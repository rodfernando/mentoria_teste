package com.mentoria.steps;

import com.mentoria.actions.NTTSiteActions;
import io.cucumber.java.Before;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;

public class NTTSiteSteps {

    NTTSiteActions nttSiteActions = new NTTSiteActions();


    @Dado("que o usuário navega para o site do Google")
    public void que_o_usuário_navega_para_o_site_do_google() throws InterruptedException {
        nttSiteActions.navegarParaSiteGoogle();
    }

    @Quando("o usuário pesquisa por vagas QA no site da NTT")
    public void o_usuário_pesquisa_por_vagas_qa_no_site_da_ntt() {
        nttSiteActions.pesquisarSiteNTTNoGoogle();
        nttSiteActions.navegarParaSiteNTT();
        nttSiteActions.navegarMenuCarreira();
        nttSiteActions.buscarVagasAbertas();
        nttSiteActions.pesquisarPorVagaQA();
        nttSiteActions.clicarNaVagaEncontrada();
    }

    @Então("deverá ser possível visualizar a vaga QA e seu título")
    public void deverá_ser_possível_visualizar_a_vaga_qa_e_seu_título() {
        nttSiteActions.verificarTituloDaVaga();
    }
}
