package com.instar.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.instar.service.MyUserDetail;

@Controller
public class ImageController {

	@Value("${file.path}")
	private String fileRealPath;

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

//	@Autowired
//	private ImageRepository mImageRepository;

//	@Autowired
//	private TagRepository mTagRepository;

//	@Autowired
//	private LikesRepository mLikesRepository;

	@GetMapping("/image/explore")
	public String imageExplore(Model model) {

//		// 알고리즘 ( 내 주변에서 좋아요가 가장 많은 순으로 해보는 것 추천)
//		Page<Image> pImages = mImageRepository.findAll(pageable);
//		List<Image> images = pImages.getContent();
//
//		// 4번 likeCount
//		for (Image item : images) {
//			int likeCount = mLikesRepository.countByImageId(item.getId());
//			item.setLikeCount(likeCount);
//		}
//		model.addAttribute("images", images);
		return "image/explore";
	}

	@GetMapping({ "/", "/image/feed" })
	public String imageFeed(@AuthenticationPrincipal MyUserDetail userDetail) {
//	public String imageFeed() {

		LOGGER.info("username = " + userDetail.getUsername());

		return "image/feed";
	}

	@GetMapping("/image/upload")
	public String imageUpload() {
		return "image/image_upload";
	}

}
