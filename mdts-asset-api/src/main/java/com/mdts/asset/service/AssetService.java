package com.mdts.asset.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.mdts.asset.model.Asset;

public interface AssetService {

	public Asset getAssetById(Long id);
	
	public List<Asset> getAssetList(Direction direction, String fieldName);
	
	public Asset create(Asset asset);
	
	public Asset update(Asset asset);
	
	public void delete(Long id);

}
