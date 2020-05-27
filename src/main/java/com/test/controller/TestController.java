package com.test.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.TestDto;
import com.test.dto.TestFilter;
import com.test.facade.TestFacade;
import com.test.response.Response;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestFacade testFacade;

	@SuppressWarnings("deprecation")
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<Long> save(@RequestBody TestDto dto) {
		if (!(dto.getStarRating() <= 5 && dto.getStarRating() >= 0.5)) {
			return Response.errorResponse("Star rating should be between 0.5 to 5", 404);
		}
		return Response.successResponse(testFacade.save(dto));
	}

	@GetMapping(value = "/{testId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<TestDto> findById(@PathVariable("testId") Long testId) {
		return Response.successResponse(testFacade.findById(testId));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<List<TestDto>> find(@ModelAttribute TestFilter filter, Pageable pageable) {
		return Response.pagedResponse(testFacade.find(filter, pageable));
	}

	@DeleteMapping(value = "/{testId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response<?> deleteById(@PathVariable("testId") Long testId) {
		testFacade.deleteById(testId);
		return Response.successResponse();
	}

}
