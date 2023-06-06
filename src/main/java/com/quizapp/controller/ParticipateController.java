package com.quizapp.controller;

import java.util.List;

import org.apache.coyote.http2.Http2OutputBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.dto.requestdto.RequestParticipateDto;
import com.quizapp.dto.requestdto.RequestQuestionAnsDto;
import com.quizapp.dto.responsedto.ResponseParticipateDto;
import com.quizapp.entity.Participate;
import com.quizapp.service.IParticipateService;

@RestController
public class ParticipateController {
	
	
	private static final Logger log = LoggerFactory.getLogger(ParticipateController.class);

	@Autowired
	private IParticipateService participateService;

//	@PostMapping("/participate")
//	public ResponseEntity<String> createParticipate(@RequestBody RequestParticipateDto requestParticipateDtos){
//		log.info("createPaticipate");
//		
//		boolean isParticipateSaved = participateService.savePaticipation(requestParticipateDtos);
//		
//		return (isParticipateSaved)?new ResponseEntity<String>("Saved ",HttpStatus.OK):
//			new ResponseEntity<String>("Unable To Save",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	@GetMapping("/participate/user/{userId}")
//	public ResponseEntity<List<ResponseParticipateDto>> getParticipateByUser(Integer userId){
//		log.info("getParticipateByUser userId = "+userId);
//		
//		List<ResponseParticipateDto> allParticipations = participateService.findAllParticipationByUser(userId);
//		
//		return new ResponseEntity<List<ResponseParticipateDto>>(allParticipations,HttpStatus.OK);
//	}
//	
//	@GetMapping("/participate/{participateId}")
//	public ResponseEntity<ResponseParticipateDto> getParticipateById(Integer participateId){
//		log.info("getParticipateById participateId = "+participateId);
//		
//		 ResponseParticipateDto responseDto = participateService.findById(participateId);
//		 
//		 return new ResponseEntity<ResponseParticipateDto>(responseDto,HttpStatus.OK);
//	}
//	
//	@GetMapping("/participate/quiz/{quizId}")
//	public ResponseEntity<List<ResponseParticipateDto>> getParticipateByQuiz(Integer quizId){
//		log.info("getParticipateByQuiz quizId = "+quizId);
//		
//		List<ResponseParticipateDto> allResponseDto = participateService.findAllParticipationByQuiz(quizId);
//		
//		return new ResponseEntity<List<ResponseParticipateDto>>(allResponseDto,HttpStatus.OK);
//	}	
//	
//	@GetMapping("/participates")
//	public ResponseEntity<List<ResponseParticipateDto>> getAllParticipate(){
//		log.info("getAllParticipate");
//		
//		List<ResponseParticipateDto> allParticipates = participateService.getAllParticipates();
//		
//		return new ResponseEntity<List<ResponseParticipateDto>>(allParticipates,HttpStatus.OK);
//	}
//	
//	
//	// TODO update participation should be created
//	
//	@DeleteMapping("/participate/{participateId}")
//	public ResponseEntity<String> deleteById(Integer participateId){
//		log.info("deleteById userId = "+participateId);
//		
//		if(!participateService.isParticipatePresent(participateId))
//			return new  ResponseEntity<String>("No Record Found",HttpStatus.BAD_REQUEST);
//		
//		boolean isDeleted = participateService.deleteById(participateId);
//		
//		return (isDeleted)?new ResponseEntity<String>("Deleted SucessFull",HttpStatus.OK):
//			new ResponseEntity<String>("Unable to Delete",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	@DeleteMapping("/participate/quiz/{quizId}")
//	public ResponseEntity<String> deleteByQuiz(Integer quizId){
//		log.info("deleteByUser userId = "+quizId);
//		
//		boolean isDeleted = participateService.deleteByQuizId(quizId);
//		
//		return (isDeleted)? new ResponseEntity<String>("Deleted Sucessfull",HttpStatus.OK):
//			new ResponseEntity<String>("Unable to Delete",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
//	@DeleteMapping("/participate/user/{userId}")
//	public ResponseEntity<String> deleteByUser(Integer userId){
//		log.info("deleteByUser userId = "+userId);
//		
//		boolean isDeleted = participateService.deleteByUserId(userId);
//		
//		return (isDeleted)?new ResponseEntity<String>("Deleted SucessFull",HttpStatus.OK):
//			new ResponseEntity<String>("Deleted UnsucessFull",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
