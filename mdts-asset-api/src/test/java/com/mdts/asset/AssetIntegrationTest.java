package com.mdts.asset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.mdts.asset.model.Asset;
import com.mdts.asset.repo.AssetRepository;

@SpringJUnitConfig
@SpringBootTest(classes = Application.class)
@Sql({"/asset_schema.sql", "/import_asset.sql"})
public class AssetIntegrationTest {
	
	@Autowired
    private AssetRepository assetRepository;
	
	@Test
    public void initialLoadDataTest() {
        assertEquals(3, assetRepository.findAll().size());
    }
	
	@Test
	public void createAssetDataTest() {
		Asset asset = Asset.builder()
				.name("iPhone")
				.type("phone")
				.description("iphone 13 pro")
				.build();
		
		//create
		asset = assetRepository.save(asset);
		
		assertNotNull(asset.getId());
		assertNotNull(asset.getCreationTime());
		assertEquals(4, assetRepository.findAll().size());
		
		
	}
	
	
}
