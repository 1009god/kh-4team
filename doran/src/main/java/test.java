/*
 * import java.io.File; import java.io.IOException;
 * 
 * import org.springframework.web.multipart.MultipartFile;
 * 
 * // int pjsellerMemNo = (int)session.getAttribute("loginNo"); //
 * pjDto.setPjSellerMemNo(pjsellerMemNo); // // int pjSeqNo =
 * pjservice.insert(pjFileVO, optionsDto); //
 * attr.addAttribute("pjSeqNo",pjSeqNo); System.out.println(pjFileVO);
 * 
 * MultipartFile mFile1 = pjFileVO.getFiles1(); MultipartFile mFile2 =
 * pjFileVO.getFiles2(); MultipartFile mFile3 = pjFileVO.getFiles3();
 * 
 * String uploadPath ="doranupload";
 * 
 * 
 * 
 * // File file = new File(uploadPath); // file.mkdir(); File files1 = new
 * File(uploadPath, mFile1.getOriginalFilename()); File files2 = new
 * File(uploadPath, mFile2.getOriginalFilename()); try {
 * mFile1.transferTo(files1); // mFile2.transferTo(files2);
 * 
 * for(MultipartFile file : mFile3) { File files3 = new File(uploadPath,
 * file.getOriginalFilename());
 * 
 * file.transferTo(files3); } } catch (IllegalStateException | IOException e) {
 * e.printStackTrace(); }
 * 
 * // MultipartFile files = pjFileVO.get
 * 
 * 
 * //file1
 * 
 * 
 * //file2
 * 
 * // // //file3
 */