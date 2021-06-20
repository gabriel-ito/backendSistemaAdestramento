CREATE TABLE `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `senha` VARCHAR(10) NOT NULL,
  `endereco` VARCHAR(60) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `data_Cadastro` DATETIME NOT NULL,
  `ativo` INT (1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `adestrador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `senha` VARCHAR(10) NOT NULL,
  `crmv` VARCHAR(8) NOT NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `data_Cadastro` DATETIME NOT NULL,
  `ativo` INT (1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `agenda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_Agenda` DATETIME NOT NULL,
  `observacao` VARCHAR(45) NULL,
  `ativo` INT (1) NOT NULL,
  `usuario_id` INT NULL,
  `adestrador_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Agenda_Usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_Agenda_Usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `Usuario` (`id`),
  INDEX `fk_Agenda_Adestrador_idx` (`adestrador_id` ASC),
  CONSTRAINT `fk_Agenda_Adestrador`
  FOREIGN KEY (`adestrador_id`)
  REFERENCES `Adestrador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE `duvida` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pergunta` VARCHAR(100) NOT NULL,
  `resposta` VARCHAR(100) NOT NULL,
  `data_Cadastro` DATETIME NOT NULL,
  `ativo` INT (1) NOT NULL,
  `usuario_id` INT NOT NULL,
  `adestrador_id` INT NULL,
  PRIMARY KEY (`id`),
    INDEX `fk_Duvida_Usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_Duvida_Usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `Usuario` (`id`),
  INDEX `fk_Duvida_Adestrador_idx` (`adestrador_id` ASC),
  CONSTRAINT `fk_Duvida_Adestrador`
  FOREIGN KEY (`adestrador_id`)
  REFERENCES `Adestrador` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
	
