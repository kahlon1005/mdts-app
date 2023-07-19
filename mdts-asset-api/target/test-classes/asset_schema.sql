drop table ASSET if exists;

create table ASSET(
  ASSET_ID int not null AUTO_INCREMENT,
  ASSET_NAME varchar(100) not null,
  ASSET_TYPE varchar(100),
  ASSET_DESC varchar(255),  
  CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY ( ASSET_ID )
);
