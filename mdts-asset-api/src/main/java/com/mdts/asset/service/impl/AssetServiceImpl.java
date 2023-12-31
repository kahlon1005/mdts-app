package com.mdts.asset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mdts.asset.model.Asset;
import com.mdts.asset.repo.AssetRepository;
import com.mdts.asset.service.AssetService;

@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetRepository repo;

	@Override
	public Asset getAssetById(Long id) {
		Asset response = repo.findById(id).orElse(null);
		if(null == response) {
			return null;
		}
		return response;
	}

	@Override
	public List<Asset> getAssetList(Direction direction, String fieldName) {
		return repo.findAll(Sort.by(direction, fieldName));		
	}

	@Override
	public Asset create(Asset asset) {
		return repo.save(asset);
		
	}

	@Override
	public Asset update(Asset asset) {	
		boolean exists = repo.existsById(asset.getId());
		if(!exists) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asset not found for update with id="+ asset.getId());
		}
		return repo.save(asset);
	}

	@Override
	public void delete(Long id) {
		Asset asset = getAssetById(id);
		if(null == asset) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asset not found with id="+ id);
		}
		repo.deleteById(id);
		
	}

	@Override
	public List<Asset> getAssetList() {
		return repo.findAll();		
	}

}
