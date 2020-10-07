package com.cognizant.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.model.PensionerDetail;

@FeignClient(name = "Pensioner-Detail-Service", url = "${feign.url}")
public interface PensionerDetailClient {

	@GetMapping("/{adhaarNumber}")
	public ResponseEntity<PensionerDetail> getAllPensionDetailByAdhaarNumber(
			@PathVariable("adhaarNumber") Long adhaarNumber);

}
