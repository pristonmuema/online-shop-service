SET foreign_key_checks = 0;
-- Create syntax for TABLE 'Category'
CREATE TABLE `Category` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `model` varchar(255) DEFAULT NULL,
                            `productCategory` enum('COMPUTER','KITCHEN_APPLIANCE','PHONE','TABLET','TELEVISION') DEFAULT NULL,
                            PRIMARY KEY (`id`)
);

-- Create syntax for TABLE 'Item'
CREATE TABLE `Item` (
                        `price` double NOT NULL,
                        `stock_id` bigint(20) DEFAULT NULL,
                        `currency` enum('KES','UGX','USD') DEFAULT NULL,
                        `description` varchar(255) DEFAULT NULL,
                        `imageUrl` varchar(255) DEFAULT NULL,
                        `itemId` varchar(255) NOT NULL,
                        `name` varchar(255) DEFAULT NULL,
                        `productId` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`itemId`),
                        UNIQUE KEY `UK_ittrdx4ba2n468k1rlwydc65n` (`stock_id`),
                        KEY `FKd2193mwctqw33925rt2bahlh5` (`productId`),
                        CONSTRAINT `FKbxmir3vphgoyh4is11ao615a1` FOREIGN KEY (`stock_id`) REFERENCES `Stock` (`id`),
                        CONSTRAINT `FKd2193mwctqw33925rt2bahlh5` FOREIGN KEY (`productId`) REFERENCES `Product` (`productId`)
);

-- Create syntax for TABLE 'Product'
CREATE TABLE `Product` (
                           `category_id` bigint(20) DEFAULT NULL,
                           `description` varchar(255) DEFAULT NULL,
                           `imageUrl` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `productId` varchar(255) NOT NULL,
                           PRIMARY KEY (`productId`),
                           UNIQUE KEY `UK_gxubutkbk5o2a6aakbe7q9kww` (`name`),
                           KEY `FKexqqeaksnmmku5py194ywp140` (`category_id`),
                           CONSTRAINT `FKexqqeaksnmmku5py194ywp140` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`)
);

-- Create syntax for TABLE 'Stock'
CREATE TABLE `Stock` (
                         `quantity` int(11) NOT NULL,
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `itemId` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_1q5atib2g1080msbbxbnaiofi` (`itemId`),
                         CONSTRAINT `FK407opi55n84rqoqjgkkekxhy9` FOREIGN KEY (`itemId`) REFERENCES `Item` (`itemId`)
);

SET foreign_key_checks = 1;