/*
 Navicat Premium Data Transfer

 Source Server         : teacherProject
 Source Server Type    : PostgreSQL
 Source Server Version : 140001
 Source Host           : localhost:5432
 Source Catalog        : CDM
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140001
 File Encoding         : 65001

 Date: 06/08/2022 21:46:10
*/


-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id" int4 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  "username" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "type" int4 DEFAULT 1,
  "isdeleted" int4 DEFAULT 0
)
;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO "public"."users" VALUES (1, 'admin', 'admin', 1, 0);

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");
