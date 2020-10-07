package com.cognizant.feign;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.model.ProcessPensionInput;

@FeignClient(name="Pension-Disbursement-service",url="http://localhost:8082/pensiondisbursement/")
public interface PensionDisbursementClient {

	@PostMapping("/disbursepension")
	public int ProcessPensionResponse(@RequestBody ProcessPensionInput processPensionInput);
	
}
