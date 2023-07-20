package com.mdts.asset.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.mdts.asset.model.Asset;
import com.mdts.asset.service.AssetService;

@ExtendWith(MockitoExtension.class)
@GraphQlTest
public class AssetGraphQLControllerTest {
	
	@Autowired
	private GraphQlTester graphQlTester; 
	
	@MockBean
    AssetService assetServiceMock;
	
	
	Asset iPhoneAsset = Asset.builder()
			.name("iPhone")
			.type("phone")
			.description("iphone 13 pro")
			.build();
	
	
	@Test
    public void testCreateAssetShouldReturnAssetId() {
		
		Asset asset = Asset.builder()
				.id(1L)
				.name("iPhone")
				.type("phone")
				.description("iphone 13 pro")
				.build();
		
		doReturn(asset).when(assetServiceMock).create(iPhoneAsset);
		
		String assertId = graphQlTester.documentName("create-asset")
			.execute()
			.path("$.data.createAsset.id")
			.entity(String.class)
			.get();
		 
		assertEquals(1, Integer.parseInt(assertId)); 
		
    }
	
	@Test
    public void testUpdateAssetShouldReturnUpdatedAssetName() {
		
		Asset asset = Asset.builder()
				.id(1L)
				.name("iPhone2") //updated
				.type("phone")
				.description("iphone 13 pro")
				.build();
		
		iPhoneAsset.setId(1L);
		
		doReturn(asset).when(assetServiceMock).update(iPhoneAsset);
		
		String assertName = graphQlTester.documentName("update-asset")
			.execute()
			.path("$.data.updateAsset.name")
			.entity(String.class)
			.get();
		 
		assertEquals("iPhone2", assertName); 
		
    }
	
	@Test
    public void testDeleteAssetShouldReturnDeletedMessage() {
		
		Asset asset = Asset.builder()
				.id(1L)
				.name("iPhone2") 
				.type("phone")
				.description("iphone 13 pro")
				.build();
		
		doNothing().when(assetServiceMock).delete(1L);
		
		String message = graphQlTester.documentName("delete-asset")
			.execute()
			.path("$.data.deleteAsset.message")
			.entity(String.class)
			.get();
		 
		assertEquals("deleted!", message); 
		
    }
	
	@Test
    public void testGetAssetByIdShouldReturnAsset() {
		
		Asset asset = Asset.builder()
				.id(1L)
				.name("BMW X5") 
				.type("Car")
				.description("Blank SUV")
				.build();
		
		doReturn(asset).when(assetServiceMock).getAssetById(1L);
		
		String assetName = graphQlTester.documentName("get-asset")
			.execute()
			.path("$.data.asset.name")
			.entity(String.class)
			.get();
		 
		assertEquals("BMW X5", assetName); 
		
    }
	
	@Test
    public void testGetAllAssetShouldReturnAssetCount() {
		
		List<Asset> assets = new ArrayList<>();
		
		Asset bmw = Asset.builder()
				.id(1L)
				.name("BMW X5") 
				.type("Car")
				.description("Blank SUV")
				.build();
		assets.add(bmw);
		Asset iphone = Asset.builder()
				.id(2L)
				.name("iPhone") 
				.type("Phone")
				.description("iPhone 13 pro")
				.build();
		assets.add(iphone);
		
		doReturn(assets).when(assetServiceMock).getAssetList();
		
		@SuppressWarnings("unchecked")
		Collection<Asset> list = graphQlTester.documentName("get-all-assets")
			.execute()
			.path("$.data.allAssets")
			.entity(Collection.class)
			.get();
		 
		assertEquals(2, list.size()); 
		
    }
	
}
