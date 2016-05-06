BEGIN TRANSACTION;
CREATE TABLE "dota" (
	`_id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`question`	TEXT NOT NULL,
	`questionimg`	INTEGER NOT NULL,
	`a1`	TEXT NOT NULL,
	`a2`	TEXT NOT NULL,
	`a3`	TEXT NOT NULL,
	`a4`	TEXT NOT NULL
);
INSERT INTO `dota` VALUES (1,'What is the name of this hero?','dotaimg1','Templar Assassin','Phantom Assassin','Drow Ranger','Anti Mage');
INSERT INTO `dota` VALUES (2,'Which hero has the highest base damage at level 1?','dotaimg2','Juggernaut','Invoker','Treant Protector','Tiny');
CREATE TABLE `android_metadata` (
	`locale`	TEXT DEFAULT 'en_US'
);
INSERT INTO `android_metadata` VALUES ('en_US');
COMMIT;
