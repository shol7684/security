package com.instar.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instar.model.User;
import com.instar.service.MyUserDetail;
import com.instar.service.UserService;

@Controller
public class UserController {

//	@Value("${file.path}")
//	private String path;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	UserService userService;

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@GetMapping("/auth/login")
	public String authLogin() {

		return "auth/login";
	}

	@GetMapping("/auth/join")
	public String authJoin() {

		LOGGER.debug("Hello Debug level log");
		LOGGER.info("Hello Info level log");
		LOGGER.error("Hello Error level log");

		return "auth/join";
	}

	@PostMapping("/auth/joinProc")
	public String authJoinProc(User user) {
		System.out.println(user);
		LOGGER.info("회원가입 폼");
		LOGGER.info(user);

		String encPwd = encoder.encode(user.getPassword());

		System.out.println(encPwd);

		user.setPassword(encPwd);

		// 유저네임 중복체크 해야함
		userService.join(user);

		return "redirect:/auth/login";
	}

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id, @AuthenticationPrincipal MyUserDetail userDetail, Model model) {

		/**
		 * 1. imageCount 2. followerCount 3. followingCount 4. User 오브젝트 (Image
		 * (likeCount) 컬렉션) 5. followCheck 팔로우 유무 (1 팔로우, 1이 아니면 언팔로우)
		 */
//
//		// 4번 임시(수정해야함)
//		Optional<User> oUser = mUserRepository.findById(id);
//		User user = oUser.get();
//
//		// 1번 imageCount
//		int imageCount = user.getImages().size();
//		model.addAttribute("imageCount", imageCount);
//
//		// 2번 followCount
//		// (select count(*) from follow where fromUserId = 1)
//		int followCount = mFollowRepository.countByFromUserId(user.getId());
//		model.addAttribute("followCount", followCount);
//
//		// 3번 followerCount
//		// (select count(*) from follower where toUserId = 1)
//		int followerCount = mFollowRepository.countByToUserId(user.getId());
//		model.addAttribute("followerCount", followerCount);
//
//		// 4번 likeCount
//		for (Image item : user.getImages()) {
//			int likeCount = mLikesRepository.countByImageId(item.getId());
//			item.setLikeCount(likeCount);
//		}
//
//		model.addAttribute("user", user);
//		// 5번
//		User principal = userDetail.getUser();
//
//		int followCheck = mFollowRepository.countByFromUserIdAndToUserId(principal.getId(), id);
//		log.info("followCheck : " + followCheck);
//		model.addAttribute("followCheck", followCheck);

		return "user/profile";
	}

	@GetMapping("/user/edit")
	public String userEdit(@AuthenticationPrincipal MyUserDetail userDetail, Model model) {

//		User user = oUser.get();
//		model.addAttribute("user", user);
		return "user/profile_edit";
	}

	@ResponseBody
	@GetMapping("/manager")
	public String manager() {

		return "매니저";
	}

	@ResponseBody
	@GetMapping("/admin")
	public String admin() {

		return "admin";
	}

	@ResponseBody
	@GetMapping("/user")
	public String user() {

		return "user";
	}

	@ResponseBody
	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@ResponseBody
	@GetMapping("/join")
	public String join() {

		return "join";
	}

	@ResponseBody
	@GetMapping("/main")
	public String main() {

		return "main";
	}

	@Secured("ROLE_ADMIN")
	@ResponseBody
	@GetMapping("/info")
	public String info() {

		return "info";
	}

	@PreAuthorize("ROLE_ADMIN") // data로 접속하기 전에 실행됨
	@ResponseBody
	@GetMapping("/data")
	public String data() {

		return "data";
	}

	@ResponseBody
	@GetMapping("/loginTest")
	public String loginTest(Authentication authentication, @AuthenticationPrincipal MyUserDetail userDetails,
			HttpServletResponse response) throws IOException {
		System.out.println("authentication = " + authentication.getPrincipal());

		MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();

		System.out.println(myUserDetail.getUser());

		System.out.println("userDetails = " + userDetails);
		System.out.println("userDetails = " + userDetails.getUsername());
		System.out.println("userDetails = " + userDetails.getUser());

		return "테스트";
	}

	@ResponseBody
	@GetMapping("/loginTest2")
	public String loginTest2(Authentication authentication, @AuthenticationPrincipal OAuth2User user)
			throws IOException {
		System.out.println("authentication = " + authentication.getPrincipal());

		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

		System.out.println(oAuth2User.getAttributes());

		System.out.println(user.getAttributes());

		return "테스트";
	}

}
