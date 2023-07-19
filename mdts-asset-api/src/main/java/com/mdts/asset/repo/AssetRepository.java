package com.mdts.asset.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mdts.asset.model.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {

	public List<Asset> findAll(Sort by);

}
