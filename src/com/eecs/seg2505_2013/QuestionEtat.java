package com.eecs.seg2505_2013;

/**
 * Created with IntelliJ IDEA.
 * User: Julien Mageau, Nicholas Horton, Nicholas Seguin, Catherine Maathuis
 * Date: 21/11/13
 * Time: 8:05 PM
 */
public enum QuestionEtat {
    EN_ATTENTE("A"), REPONDUE("REP"), REFUSEE("REF");

    String codeEtat;

    /**
     * Enumerateur utilise pour indique l'etat d'une question
     *
     * @param c Le code indiquant l'etat de la question
     */
    QuestionEtat(String c) {
        this.codeEtat = c;
    }

    public String getCodeEtat() {
        return codeEtat;
    }
}
