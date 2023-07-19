package com.mdts.asset.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mdts.asset.model.Asset;
import com.mdts.asset.service.AssetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/asset")
public class AssetController {
	
	@Autowired
	private AssetService service;

	@GetMapping
	public ResponseEntity<List<Asset>> getAssetList(@RequestParam String sort, @RequestParam String fieldName){
		log.debug("AssetController, getAssetList begin sort={} by field={}", sort, fieldName);
		try {
			List<Asset> response = service.getAssetList(Direction.fromString(sort), fieldName);
			log.debug("AssetController, getAssetList end sort={} by field={}", sort, fieldName);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Asset> getAssetById(@PathVariable Long id){
		log.debug("AssetController, getAsset begin get asset by id={}", id);
		Asset response = service.getAssetById(id);
		log.debug("AssetController, getAsset end get asset by id={}", id);
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Asset> create(@RequestBody @Valid Asset asset){
		Asset response = service.create(asset);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Asset> update(@RequestBody @Valid Asset asset){
		if(null == asset.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Asset Id is required for update.");
		}
		service.update(asset);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Asset> create(@PathVariable Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	

}
