drop table T_ACCOUNT_BENEFICIARY if exists;
drop table T_ACCOUNT_CREDIT_CARD if exists;
drop table T_ACCOUNT if exists;
drop table T_RESTAURANT if exists;
drop table T_REWARD if exists;
drop sequence S_REWARD_CONFIRMATION_NUMBER if exists;
drop table DUAL_REWARD_CONFIRMATION_NUMBER if exists;

create table T_ACCOUNT (ID integer identity primary key, NUMBER varchar(9), NAME varchar(50) not null, CREDIT_CARD varchar(16), unique(NUMBER));
create table T_ACCOUNT_BENEFICIARY (ID integer identity primary key, ACCOUNT_ID integer, NAME varchar(50), ALLOCATION_PERCENTAGE double not null, SAVINGS double not null, unique(ACCOUNT_ID, NAME));
create table T_RESTAURANT (ID integer identity primary key, MERCHANT_NUMBER varchar(10) not null, NAME varchar(80) not null, BENEFIT_PERCENTAGE double not null, BENEFIT_AVAILABILITY_POLICY varchar(1) not null, unique(MERCHANT_NUMBER));
create table T_REWARD (ID integer identity primary key, CONFIRMATION_NUMBER varchar(25) not null, REWARD_AMOUNT double not null, REWARD_DATE date not null, ACCOUNT_NUMBER varchar(9) not null, DINING_AMOUNT double not null, DINING_MERCHANT_NUMBER varchar(10) not null, DINING_DATE date not null, unique(CONFIRMATION_NUMBER));

create sequence S_REWARD_CONFIRMATION_NUMBER start with 1;
create table DUAL_REWARD_CONFIRMATION_NUMBER (ZERO integer);
insert into DUAL_REWARD_CONFIRMATION_NUMBER values (0);
       
alter table T_ACCOUNT_BENEFICIARY add constraint FK_ACCOUNT_BENEFICIARY foreign key (ACCOUNT_ID) references T_ACCOUNT(ID) on delete cascade;