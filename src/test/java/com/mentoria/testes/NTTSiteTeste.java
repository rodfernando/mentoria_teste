package com.mentoria.testes;

import com.mentoria.actions.NTTSiteActions;
import org.junit.jupiter.api.Test;

public class NTTSiteTeste extends NTTSiteActions {

    @Test
    public void teste() {
        navegarParaSiteGoogle();
        pesquisarSiteNTTNoGoogle();
        navegarParaSiteNTT();
        navegarMenuCarreira();
        buscarVagasAbertas();
        pesquisarPorVagaQA();
        clicarNaVagaEncontrada();
        verificarTituloDaVaga();
    }
}
