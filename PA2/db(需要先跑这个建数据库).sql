/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : best_duck

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 05/05/2022 00:59:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_num` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sku` bigint NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `zip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `shipping_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `credit_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `exp_mon` int NULL DEFAULT NULL,
  `exp_year` int NULL DEFAULT NULL,
  `cvv` int NULL DEFAULT NULL,
  `phone_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  #`datetime` datetime NULL DEFAULT NULL,
  `datetime` date NOT NULL,
  PRIMARY KEY (`order_num`, `sku`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
#INSERT INTO `order` VALUES ('1231', 6338995, 'Joe', 'Biden', 'abc', 'Beijing', 'Beijing', '00000', '4242', 2, 24242, 24242, 4242, 4242, 4242, '24242', '0', NULL);
#INSERT INTO `order` VALUES ('12313', 6338995, 'Donald', 'Trump', 'abc', 'Beijing', 'Beijing', '00000', '12131', 4, 12313, 12312, 12321, 12321, 12312, '1231', '0', NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `sku` int NULL DEFAULT NULL,
  `producer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `stock` int NULL DEFAULT NULL,
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (6338995, 'WD', 'Storage', 'WD_BLACK SN750 NVMe Gaming 1TB PCIe Gen 3 x4 Internal Solid State Drive', './img/products/hard_drives_&_ssd_&_storage/Gaming.jpg', 59.90, 5, 'Upgrade compatible PC systems with the WD_Black SN750 internal SSD. Read speeds of up to 3,470MB/s offer improved load times, while the M.2 form factor means you can directly plug into the motherboard without extra cabling. Compared to typical 2.5-inch SSDs, this WD_Black SSD fits double the storage density on a single-sided drive, making it suitable for portable builds.');
INSERT INTO `product` VALUES (6324470, 'Samsung', 'Storage', '970 EVO Plus 1TB PCIe Gen 3 x4 NVMe Internal Solid State Drive with V-NAND Technology', './img/products/hard_drives_&_ssd_&_storage/970.jpg', 59.90, 10, 'The ultimate in speed and performance Speed up your computer with this Samsung 970 Evo Plus 1TB internal SSD. It maximizes the potential of NVMe bandwidth for unbeatable computing The M.2 interface is capable of delivering read speeds up to 3,500MB/sec. and write speeds up to 3,300MB/sec., so you can finish work faster. Samsung\'s innovative technology empowers you with the capacity to do more and accomplish more. The 970 EVO Plus provides exceptional endurance powered by the latest V-NAND technology and Samsung\'s reputation for quality. This Samsung 970 Evo Plus 1TB internal SSD has Dynamic Thermal Guard technology for protection against overheating.');
INSERT INTO `product` VALUES (9315347, 'WD', 'Storage', 'WD_BLACK Gaming 4TB Internal SATA Hard Drive for Desktops', './img/products/hard_drives_&_ssd_&_storage/SN750.jpg', 139.90, 20, 'Store edited photos, videos, and games on this 3.5-inch Western Digital hard drive. It has dynamic cache for optimizing allocation between reads and writes, and there\'s plenty of space to store files with its 4TB capacity.');
INSERT INTO `product` VALUES (6467500, 'MSI', 'GPUs', 'NVIDIA GeForce RTX 3070 Ti VENTUS 3X OC 8GB GDDR6 PCI Express 4.0 Graphics Card', './img/products/video_graphics_cards/3070.jpg', 849.90, 10, 'The GeForce RTX™ 3070 Ti graphics card is powered by Ampere—NVIDIA’s 2nd gen RTX architecture. Built with enhanced RT Cores and Tensor Cores, new streaming multiprocessors, and superfast G6X memory, it gives you the power you need to rip through the most demanding games. VENTUS brings a performance-focused design that maintains the essentials to accomplish any task at hand. A capable triple fan arrangement laid into a rigid industrial design lets this sharp looking graphics card fit into any build. A brushed metal backplate provides a clean visual finish to the card. It also strengthens the card and thanks to some cleverly place thermal pads even helps to keep the temperature low.');
INSERT INTO `product` VALUES (6467808, 'EVGA', 'GPUs', 'EVGA - NVIDIA GeForce RTX 3080 Ti FTW3 ULTRA GAMING 12GB GDDR6X PCI Express 4.0 Graphics Card', './img/products/video_graphics_cards/3080.jpg', 1349.90, 0, 'The EVGA GeForce RTX 3080 Ti delivers the unprecedented performance that gamers crave for 4K resolution gaming, powered by the NVIDIA Ampere architecture. It\'s built with enhanced RT Cores and Tensor Cores, new streaming multiprocessors, and superfast G6X memory for an amazing gaming experience. Combined with the next generation of design, cooling, and overclocking with EVGA Precision X1, the EVGA GeForce RTX 3080 Series presents a new definition in ultimate performance.');
INSERT INTO `product` VALUES (6483672, 'Intel', 'Processors', 'Core i7-12700K Desktop Processor 12 (8P+4E) Cores up to 5.0 GHz\r\nCore i7-12700K Desktop Processor 12 (8P+4E) Cores up to 5.0 GHz', './img/products/cpus_&_processors/Core_i7.jpg', 369.99, 10, '12th Gen Intel® Core™ i7-12700K unlocked desktop processor. Featuring Intel® Turbo Boost Max Technology 3.0 and PCIe Gen 4.0 support, unlocked 12th Gen Intel® Core™ desktop processors are optimized for enthusiast gamers and serious creators and help deliver high performance overclocking for an added boost. Thermal solution NOT included in the box. Compatible with 600 series chipset based motherboards 125W.');
INSERT INTO `product` VALUES (6438942, 'AMD', 'Processors', 'Ryzen 9 5900X 4th Gen 12-core, 24-threads Unlocked Desktop Processor', './img/products/cpus_&_processors/Ryzen_9.jpg', 399.00, 5, '12 cores to power through gaming, streaming and more. Get the high-speed gaming performance of the world’s best desktop processor. A fast and easy way to expand and accelerate the storage in a desktop PC with an AMD Ryzen processor.');
INSERT INTO `product` VALUES (6365750, 'PNY', 'RAMs', '16GB 2.666GHz PC4-21300 DDR4 SO-DIMM Unbuffered Non-ECC Laptop Memory', './img/products/memory(ram)/PNY.webp', 59.99, 10, ' Boost your PC\'s response times while handling intensive tasks with this PNY DDR4 2666MHz notebook memory. The 2666MHz RAM frequency and DDR4 technology offer high speed and improved thermal performance. This PNY DDR4 2666MHz notebook memory is compatible with Windows 10 and previous versions of Windows operating software for flexible installation.');
INSERT INTO `product` VALUES (6449223, 'Corsair', 'RAMs', 'VENGEANCE RGB PRO 32GB (2PK x 16GB) 3600MHz DDR4 C18 DIMM Desktop Memory', './img/products/memory(ram)/PNY.webp', 152.99, 5, 'CORSAIR VENGEANCE RGB PRO Series DDR4 memory lights up your PC with mesmerizing dynamic multi-zone RGB lighting, while delivering the best in DDR4 performance and stability. Every module boasts ten individually controlled RGB LEDs, while wire-free design makes installation simple. Take control with CORSAIR iCUE software and completely customize every module’s lighting to match your system, or easily synchronize lighting across all your CORSAIR products with Light LINK. A custom designed PCB provides the highest signal quality for the best level of performance and stability on the latest AMD and Intel DDR4 motherboards, while specially screened ICs unlock superior overclocking.');
INSERT INTO `product` VALUES (6356983, 'ASUS', 'Motherboards', 'ASUS - TUF GAMING X570-PLUS (WI-FI) (Socket AM4) USB-C Gen2 AMD Motherboard with LED Lighting', './img/products/motherboards/X570.jpg', 201.99, 10, 'Get the power you need to play demanding games with this ASUS TUF gaming motherboard. The AMD chipset provides power and compatibility with Ryzen processors while Gigabit Ethernet, Bluetooth 5 and 802.11ac Wi-Fi deliver fast connectivity. Active cooling and multiple heat sinks keep this ASUS TUF gaming motherboard running at peak performance.');
INSERT INTO `product` VALUES (6455363, 'MSI', 'Motherboards', 'Z590-A PRO (Socket 1200) USB-C Gen 2 Intel ATX Motherboard PCIE Gen 2', './img/products/motherboards/Z590_A.jpg', 179.99, 10, 'The Z590-A PRO ATX motherboard employs a robust VRM platform for the Intel Z590 chipset (LGA 1200, 11th Gen Core ready) with a 12 Duet Rail 55A power system (4+8 Pin connector) supported by passive cooling heat sinks and Frozr AI thermal monitoring. Designed to support the latest 11th Gen Intel Core processors, the Z590-A PRO includes future-friendly hardware support options in the form of 4 x DDR4 dual-channel DIMMs (5333 MHz/OC) and a steel-reinforced PCIe 4.0 x16 slot to support heavier GPU’s (2-way AMD CrossFire available via secondary PCIe 3.0 x16 slot). Storage options include 1 x M.2 Gen4 x4 64Gb/s slot (with Shield Frozr) and 2 x M.2 Gen3 x4 32Gb/s slots for ultra-fast SSD access (compatible with Intel Optane). Hard-line connectivity options include Intel 2.5G LAN, HD Audio 7.1 with Audio Boost, and HDMI 2.0b (4K/60Hz) outputs. For creative professionals looking to update their system, the MSI Z590-A PRO offers a quality platform with the capacity to support a broad spectrum of both cutting-edge and legacy hardware.');
INSERT INTO `product` VALUES (6366008, 'NZXT', 'Cases', 'NZXT - H510 Compact ATX Mid-Tower Case with Tempered Glass - Matte White', './img/products/computer_cases/H510i.jpg', 89.99, 5, '\r\nBuild a small-footprint PC without sacrificing on features using this white NZXT compact mid-tower computer case. The cable management system includes preinstalled channels for seamless, intuitive routing, while the removable radiator mounting brackets offer additional customization options. This NZXT compact mid-tower computer case features a front-facing USB-C port for connecting smartphones and external storage devices easily.');
INSERT INTO `product` VALUES (6370550, 'Corsair', 'Cases', 'CORSAIR - iCUE 220T RGB Airflow ATX Mid-Tower Smart Case - Black', './img/products/computer_cases/iCue220T.jpg', 109.99, 10, 'Build a PC system using this Corsair iCUE mid-tower ATX smart case. The high airflow layout and three 120mm RGB fans ensure optimal cooling, while the tempered glass side panel lets you show off your components. This Corsair iCUE mid-tower ATX smart case features flexible storage options with dual 2.5-inch bays and two removable trays.');
INSERT INTO `product` VALUES (6459246, 'Corsair', 'Power_Supplies', 'CORSAIR - RMx Series RM1000x 80 PLUS Gold Fully Modular ATX Power Supply - Black', './img/power_supplies/RM1000x.jpg', 168.99, 10, '\r\nCORSAIR RM1000x Series power supplies with EPS12V connectors are built with the highest quality components to deliver 80 PLUS Gold efficient power to your PC. RM1000x PSUs use only Japanese 105°C capacitors, for long life and reliability backed by a ten-year warranty. A 135mm magnetic levitation bearing fan ensures high performance coupled with low noise. Wake your computer faster and consume less power with support for Modern Standby sleep mode. Zero RPM mode ensures virtually silent operation at low and medium loads. Fully modular cables make PC builds and upgrades easy, as you need only connect the cables your system requires. An RM1000x PSU is quiet, efficient and dependable enough to power your high-performance PC for years to come.');
INSERT INTO `product` VALUES (6209800, 'EVGA', 'Power_Supplies', 'SuperNOVA 1000W ATX 80 Plus Gold Fully Modular Power Supply', './img/power_supplies/SuperNova.jpg', 169.99, 0, 'The EVGA SuperNOVA G+ Power Supplies have arrived. Launched in 2013, the EVGA SuperNOVA G1 power supplies have been among the longest-lasting and best-performing power supplies on the market. Despite only a few changes over the years, it\'s only fitting that these are due for an upgrade. Enter the G+, the new and improved fully-modular power supply line-up from EVGA with an 80+ Gold Efficiency rating.Compared to the EVGA SuperNOVA G1 power supplies, the G+ are up to 20mm shorter, feature 100% Japanese capacitors, are up to 20% quieter, have much tighter 12v. load regulation, and have a superior fluid-dynamic bearing fan. Combining a full set of current, voltage, and temperature protections with EVGA\'s World Leading 10-Year Warranty, the EVGA SuperNOVA G+ power supplies set the new Gold standard for performance and value.');
INSERT INTO `product` VALUES (6447507, 'NZXT', 'Coolings', 'Kraken Z63 280mm', './img/fans_heatsinks_cooling/Kraken_Z63.jpg', 220.99, 10, 'The all-new Kraken Z Series lets you personalize your all-in-one liquid cooler like never before Through CAM’s unique software interface you can do more than simply fine-tune settings you can now display your favorite images and animated gifs or CAM system information allowing for total customization Backed by a 6-year the Kraken Z Series provides superior performance in liquid cooling simple installation and a look that is uniquely your own.');
INSERT INTO `product` VALUES (6422732, 'Corsair', 'Coolings', 'CORSAIR - iCUE H100i ELITE CAPELLIX CPU Cooler - Black', './img/fans_heatsinks_cooling/iCUE_H100i.jpg', 149.99, 5, ' The CORSAIR iCUE H100i ELITE CAPELLIX Liquid CPU Cooler delivers powerful, low-noise cooling for your CPU, with a 240mm radiator and two CORSAIR ML120 RGB Magnetic Levitation PWM fans controllable between 400 RPM and 2,400 RPM. 33 Ultra-bright CAPELLIX LEDs on the pump head and eight RGB LEDs per fan let you completely personalize your cooler’s look. Connect to the included CORSAIR iCUE Commander CORE and powerful CORSAIR iCUE software to orchestrate synchronized RGB lighting, adjust fan speeds, and enable Zero RPM mode on up to six fans. Easy to install on most major CPU sockets, with a thermally optimized split-flow cold plate and low-noise centrifugal pump for exceptional performance, the H100i ELITE CAPELLIX puts the spotlight on your cooling.');

-- ----------------------------
-- Table structure for rating
-- ----------------------------
DROP TABLE IF EXISTS `rating`;
CREATE TABLE `rating`  (
  `userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sku` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `orderid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `score` int NULL DEFAULT NULL,
  PRIMARY KEY (`sku`, `orderid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rating
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
