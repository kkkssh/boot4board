package org.iclass.mvc.controller;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.CommunityComments;
import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.PageResponseDTO;
import org.iclass.mvc.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/community")
@Log4j2
@RequiredArgsConstructor
public class CommunityController {

	private final CommunityService service;

	// 여기서부터는 요청을 처리하는 핸들러 메소드입니다.
	// URL 설계 : 글 목록 조회는 /community/list, 글쓰기 /community/write,
	// 글읽기 /community/read

	@GetMapping("/alert")
	public void alert() {

	}

	@GetMapping("/list")
	// 파라미터 값이 없으면 오류. 기본값 설정합니다.
	public void pagelist(
			PageRequestDTO pageRequestDTO, Model model) {

		/*model.addAttribute("list", service.pagelist(page).get("list"));
		model.addAttribute("paging", service.pagelist(page).get("paging"));
		model.addAttribute("today", LocalDate.now());*/

		log.info(">>>>>> pageRequestDTO: {}", pageRequestDTO);

		PageResponseDTO responseDTO = service.listWithSearch(pageRequestDTO);

		responseDTO.getList().forEach(i ->{
			log.info(">>>>> 글 : {}",i);
		});

		model.addAttribute("paging", responseDTO);

		// List.html에 전달할 model 관련 코드 작성하시고 List.html 도 완성해보세요. 레이아웃도 적용해 보세요.
	}

	@GetMapping("/read") // url community/read?ids=961&page=1
	public void read(PageRequestDTO pageRequestDTO, long idx,
			// 파라미터로 받은 값을 Model 객체-model.addAttribute 와 같이 저장합니다.
			 Model model) {
		Community community = service.read(idx);
		model.addAttribute("dto", community);
		model.addAttribute("cmtlist", service.commentsList(idx));
	}

	@GetMapping("/write")
	public void write() {
		// 글쓰기 GET 요청은 view name 만 지정하고 끝.

	}

	@PostMapping("/write")
	// 파라미터가 많을 때, 그것들을 필드로 갖는 dto 또는 map 타입으로 전달받기
	public String save(Community dto,
				RedirectAttributes reAttr
				) {
		log.info("dto : {}", dto);
		service.insert(dto);
		
		reAttr.addFlashAttribute("message", "글 등록이 완료되었습니다.");
		return "redirect:/community/list";
	}
	// location.href='list.jsp' 는 자바스크립트-클라이언트 브라우저에서 주소 변경
	// response.sendRedirect("list.jsp") 는 서버에서 클라이언트가
	// 새로 보낼 요청을 강제로 실행.
	// ㄴ POST 요청을 처리한 후에는 redirect를 합니다.

	@PostMapping("/update")
	public void update(long idx, @ModelAttribute("page") 
				int page, Model model) {
		model.addAttribute("dto", service.selectByIdx(idx));
		// int page @ModelAttribute 로 model.addAttribute("page",page); 
		// 를 대신해서 update.html로 전달합니다.
	}

	@PostMapping("/save")
	public String updateAction(int page, 
			Community dto, 
//			Model model
			RedirectAttributes redirectAttributes
			) {
		
		service.update(dto);
		
		// return "redirect:/community/list?page=" + page;
		
		// 수정 후 다시 글 상세보기
		redirectAttributes.addAttribute("idx", dto.getIdx());
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addFlashAttribute("message", "글 수정이 완료되었습니다.");
		return "redirect:/community/read";
	}

	@PostMapping("/delete")
	public String delete(@ModelAttribute("page") int page, 
			Long idx,
			RedirectAttributes reAttr
			) {
		
		service.delete(idx);
		reAttr.addFlashAttribute("message", "글 삭제가 완료되었습니다.");
		reAttr.addAttribute("page", page);
		// @ModelAttribute 와 RedirectAttributes 충돌하므로 직접 코딩함.
		return "redirect:/community/list";
	}
	
	// 댓글
	@PostMapping("/comments")
	public String comments(int page, int f, 
			CommunityComments dto, RedirectAttributes redirectAttributes) {
		log.info(">>>>>>>>>>>>>> dto : {}", dto);
		service.comments(dto, f);
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addAttribute("idx", dto.getMref());
		
		String message = null;
		if(f==1) message = "댓글 등록 하였습니다.";
		else if(f==2) message = "댓글 삭제 하였습니다.";
		redirectAttributes.addFlashAttribute("message", message);
		
//		return "redirect:/community/read?page="+page+"&idx="+dto.getMref();
		// 리다이렉트 애트리뷰트 사용하므로 쿼리 스트링 안씁니다.
		return "redirect:/community/read";	
	}
	
}
