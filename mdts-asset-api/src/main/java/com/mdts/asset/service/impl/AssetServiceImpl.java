package com.mdts.asset.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

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
		return repo.save(asset);
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);
	}

}
