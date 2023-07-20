package com.mdts.asset.controller;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import com.mdts.asset.dto.AssetInput;
import com.mdts.asset.dto.Message;
import com.mdts.asset.dto.Sort;
import com.mdts.asset.model.Asset;
import com.mdts.asset.service.AssetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AssetGraphQLController {
	
	private final AssetService assetService;
	
	public AssetGraphQLController(AssetService assetService) {
		this.assetService = assetService;
	}
	

	@QueryMapping
	public List<Asset> allAssets(){
		return assetService.getAssetList();		
	}
	
	 
	@QueryMapping
	public List<Asset> allSortAssets(@Argument Sort sort, @Argument String fieldName){
		log.debug("AssetGqlController, getAssets begin sort={} by field={}", sort, fieldName);
		try {
			List<Asset> response = assetService.getAssetList(Direction.fromString(sort.name()), fieldName);
			log.debug("AssetGqlController, getAssets end sort={} by field={}", sort, fieldName);
			return response;
			
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}
	}
	
	@QueryMapping
	public Asset asset(@Argument Long id){
		log.info("AssetGqlController, get asset for id={}", id);
		return assetService.getAssetById(id);		
	}
	
	@MutationMapping
	public Asset createAsset(@Argument AssetInput input) {
		log.info("AssetGqlController, create asset name={}", input.getName());
		Asset asset = Asset.builder()
				.name(input.getName())
				.type(input.getType())
				.description(input.getDescription())
				.build();
		
		return assetService.create(asset);
	}
    
	@MutationMapping
	public Asset updateAsset(@Argument Long id, @Argument AssetInput input) {
		log.info("AssetGqlController, update asset id={}", id);
		Asset asset = Asset.builder()
				.id(id)
				.name(input.getName())
				.type(input.getType())
				.description(input.getDescription())
				.build();
		
		return assetService.update(asset);
	}
	
	@MutationMapping
    public Message deleteAsset(@Argument Long id) {
		log.info("AssetGqlController, delete asset id={}", id);
		assetService.delete(id);
    	return new Message("deleted!");
    }
	

}
