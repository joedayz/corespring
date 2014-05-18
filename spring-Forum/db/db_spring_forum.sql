CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `fecha_publicacion` datetime NOT NULL,
  `hash_password` varchar(128) NOT NULL,
  `login` varchar(32) NOT NULL,
  `nombre` varchar(128) NOT NULL,
  `twitter` varchar(64) DEFAULT NULL,
  `ultimo_login` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `twitter` (`twitter`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `tema` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `permiso_usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(64) NOT NULL,
  `usuario_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK86973484F9C1E71F` (`usuario_id`),
  CONSTRAINT `FK86973484F9C1E71F` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `topico` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_publicacion` datetime NOT NULL,
  `titulo` varchar(128) NOT NULL,
  `autor_id` bigint(20) NOT NULL,
  `tema_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FKCC42D9205826AD95` (`tema_id`),
  KEY `FKCC42D920C198118A` (`autor_id`),
  CONSTRAINT `FKCC42D9205826AD95` FOREIGN KEY (`tema_id`) REFERENCES `tema` (`id`),
  CONSTRAINT `FKCC42D920C198118A` FOREIGN KEY (`autor_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `usuario` (`id`, `email`, `fecha_publicacion`, `hash_password`, `login`, `nombre`, `twitter`, `ultimo_login`)
VALUES
  (1, 'jamdiazdiaz@gmail.com', '2013-05-23 20:09:31', '6ec74178d214d9cf7826704e42f9312cda56eb5f8e0af98df0e94f92bd466dab', 'jamdiazdiaz', 'Jose Diaz', NULL, NULL);



INSERT INTO `permiso_usuario` (`id`, `role`, `usuario_id`)
VALUES
  (1, 'ROLE_ADMIN', 1),
  (2, 'ROLE_MEMBER', 1);





