package com.mentoria.maps;

import org.openqa.selenium.By;

public interface NTTSiteMap {

    By cookiesNTTSite = By.xpath("//div[@class='inner']");
    By cookiesNTTsiteAccept = By.id("all");
    By menuNTTSite = By.cssSelector("i.icon-menu");
    By menuCarreiraNTTSite = By.xpath("//span[text()='Carreira']");
    By botaoBuscarVagasAbertas = By.cssSelector("a.button.primary.dark");
    By campoPesquisaVagasNTTsite = By.xpath("//input[starts-with(@id, 'pesquisar')]");
    By detalhesVagaEcontrada = By.id("ver-detalhes-0");
    By vagaEncontrada = By.xpath("//small[contains(text(), 'vaga encontrada')]");
    By tituloQA = By.xpath("//h1[contains(text(), 'QA')]");
}