/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmg.hc.telessaude.diagnostico.dominio.commons;

/**
 *
 * @author igor.santos
 */
public abstract class Mensagens {

    private static String CAMPO_VAZIO = "Por favor, informe o campo #.";
    /**
     * Deve selecionar pelo menos um Registro.
     */
    public static final String SELECIONE_AO_MENOS_UM = "Deve selecionar pelo menos um Registro.";
    /**
     * Apenas um Registro deve ser selecionado por vez.
     */
    public static final String SELECIONE_APENAS_UM = "Apenas um Registro deve ser selecionado por vez.";
    /**
     * Deseja realmente excluir este(s) registro(os) ?
     */
    public static final String CONFIRMAR_EXCLUSAO = "Deseja realmente excluir este(s) registro(os) ?";
    /**
     * Confirma a Operação ?
     */
    public static final String CONFIRMAR_OPERACAO = "Confirma a Operação ?";
    /**
     * Certeza que deseja cancelar a operação ?
     */
    public static final String CONFIRMAR_CANCELAR = "Certeza que deseja cancelar a operaçao ?";
    /**
     * Operação realizada com sucesso.
     */
    public static final String SUCESSO = "Operação realizada com sucesso.";
    /**
     * Nao a possavel excluir elemento, que ja possui relacionamento.
     */
    public static final String EXCLUSAO_RELACIONAMENTO = "Nao a possavel excluir elemento, que ja possui relacionamento.";
    
    /**
     * Senha nao confere com a confirmação.
     */
    public static final String SENHA_NAO_CONFERE = "Senha não confere com a confirmação.";
    
    /**
     * "Sua Senha Atual est? incorreta."
     */
    public static final String SENHA_ATUAL_INCORRETA = "Sua Senha Atual esta incorreta.";
    
    /**
     * Selecione uma prioridade.
     */
    public static final String SELECIONE_UMA_PRIORIDADE = "Selecione uma prioridade.";
    
    /**
     * Selecione uma prioridade.
     */
    public static final String PREENCHER_CAMPO = "Conteado do campo deve ser preenchido.";
    
    /**
     * Deseja realmente encerrar o plantao ?
     */
    public static final String CANCELAR_PLANTAO = "Deseja realmente encerrar o plantão ?";
    
    /**
     * Nao foi possavel iniciar plantao
     */
    public static final String PLANTAO_INVALIDO = "Nao foi possavel iniciar plantão.";
    
    /**
     * Confirma a Gravaaao do Laudo ?
     */
    public static final String CONFIRMAR_LAUDO = "Confirma a Gravaaao do Laudo ?";
    
    /**
     * Nao a possavel efetuar a gravação de um laudo em branco!
     */
    public static final String LAUDO_EM_BRANCO = "Nao a possavel efetuar a gravação de um laudo em branco!";
   
    
    // titulos
    public static final String TITLE_SUCESSO = "Sucesso";
    public static final String TITLE_PREENCHIMENTO_OBRIGATORIO = "Preenchimento Obrigatario.";
    public static final String TITLE_ATENCAO = "Atenção";
    public static final String TITLE_ALERTA = "Alerta";
    public static final String TITLE_SELECIONE_UM_REGISTRO = "Selecione um Registro";
    public static final String TITLE_LAUDO_ECG = "Laudo ECG";
    
    private Mensagens() {
    }

    public static String getCampoVazio(String elemento) {
        return CAMPO_VAZIO.replace("#", elemento);
    }
}
