package com.mentoria.actions;

import com.mentoria.core.BaseTest;
import com.mentoria.database.DataBase;
import com.mentoria.maps.GoogleSiteMap;
import com.mentoria.maps.NTTSiteMap;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * Classe NTTSiteActions que contém ações automatizadas a serem realizadas no site NTT.
 */
public class NTTSiteActions extends BaseTest implements GoogleSiteMap, NTTSiteMap, DataBase {

    public final String PAGE_URL_GOOGLE = "https://www.google.com/";

    /** Navega para a página do Google. */
    public void navegarParaSiteGoogle() {
        driver.navigate().to(PAGE_URL_GOOGLE);
    }

    /** Realiza uma pesquisa no Google pelo site NTT. */
    public void pesquisarSiteNTTNoGoogle() {
        WebElement caixaDeTexto = driver.findElement(caixaDePesquisaGoogle);
        caixaDeTexto.sendKeys(textoPesquisaNTT);
        Actions actions = new Actions(driver);
        actions.sendKeys(caixaDeTexto, Keys.ENTER).build().perform();
        buscarLinkPorTexto(textoPesquisaNTT);
    }

    /** Navega para o site NTT após encontrar o link. */
    public void navegarParaSiteNTT() {
        clicarNoLinkNTT();
        aceitarCookies();
    }

    /** Navega para o menu de carreira no site NTT. */
    public void navegarMenuCarreira() {
        menuCarreira();
    }

    /** Busca por vagas abertas no site NTT. */
    public void buscarVagasAbertas() {
        clicarVagasAbertas();
    }

    /** Pesquisa por vagas de QA no site NTT. */
    public void pesquisarPorVagaQA() {
        esperarElementoAparecer(campoPesquisaVagasNTTsite);
        WebElement campo = driver.findElement(campoPesquisaVagasNTTsite);
        campo.sendKeys(vagaQA);
        campo.click();
    }

    /** Clica na vaga encontrada após a pesquisa. */
    public void clicarNaVagaEncontrada() {
        esperarElementoAparecer(vagaEncontrada);
        Assert.assertTrue(driver.findElement(vagaEncontrada).isDisplayed(), "Elemento 'vaga encontrada' não encontrado na tela.");
        driver.findElement(detalhesVagaEcontrada).click();
    }

    /** Clica no link da NTT caso esteja presente. */
    public void clicarNoLinkNTT() {
        try {
            WebElement nttLink = buscarLinkPorTexto(textoPesquisaNTT);
            if (nttLink != null) {
                nttLink.click();
                System.out.println("Link '" + textoPesquisaNTT + "' clicado com sucesso!");
            } else {
                throw new NoSuchElementException("O link '" + textoPesquisaNTT + "' não foi encontrado.");
            }
        } catch (NoSuchElementException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Busca um link por seu texto visível na página.
     *
     * @param textoPesquisado O texto a ser pesquisado no link.
     * @return O elemento Web do link encontrado ou null se não encontrado.
     */
    public WebElement buscarLinkPorTexto(String textoPesquisado) {
        esperarElementoAparecer(linksNTT);
        List<WebElement> links = driver.findElements(linksNTT);
        return links.stream()
                .filter(link -> link.getText().trim().equalsIgnoreCase(textoPesquisado))
                .findFirst()
                .orElse(null);
    }

    /** Espera um elemento aparecer na página. */
    public void esperarElementoAparecer(By elemento) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elemento));
    }

    /** Aceita os cookies exibidos pelo site NTT. */
    public void aceitarCookies() {
        esperarElementoAparecer(cookiesNTTSite);
        driver.findElement(cookiesNTTsiteAccept).click();
    }

    /** Navega até o menu de carreira no site NTT. */
    public void menuCarreira() {
        driver.findElement(menuNTTSite).click();
        esperarElementoAparecer(menuCarreiraNTTSite);
        driver.findElement(menuCarreiraNTTSite).click();
    }

    /** Clica no botão que busca vagas abertas e muda de janela. */
    public void clicarVagasAbertas() {
        esperarElementoAparecer(botaoBuscarVagasAbertas);
        mudarDeJanelaAoClicar();
    }

    /** Muda para a nova janela aberta após clicar no botão. */
    public void mudarDeJanelaAoClicar() {
        String abaOrigemNTT = driver.getWindowHandle();
        driver.findElement(botaoBuscarVagasAbertas).click();
        Set<String> todasAbas = driver.getWindowHandles();
        for (String abaAtual : todasAbas) {
            if (!abaAtual.equals(abaOrigemNTT)) {
                driver.switchTo().window(abaAtual);
                break;
            }
        }
    }

    /** Verifica o título da vaga de QA. */
    public void verificarTituloDaVaga() {
        esperarElementoAparecer(tituloQA);
        String textoObtido = driver.findElement(tituloQA).getText();
        System.out.println(textoObtido);
        Assertions.assertEquals(textoObtido, textoTituloVagaQA);
    }
}
