package authenticityVerifier;

import java.util.HashSet;
import java.util.Set;

public class SpamVerifier {

    // Set of spam keywords
    private static final Set<String> SPAM_KEYWORDS = new HashSet<>();
    static {
        SPAM_KEYWORDS.add("grátis");
        SPAM_KEYWORDS.add("oferta limitada");
        SPAM_KEYWORDS.add("compre agora");
        SPAM_KEYWORDS.add("garantido");
        SPAM_KEYWORDS.add("clique aqui");
        SPAM_KEYWORDS.add("entre");
        SPAM_KEYWORDS.add("preencha");
        SPAM_KEYWORDS.add("prêmio");
        SPAM_KEYWORDS.add("você foi selecionado");
        SPAM_KEYWORDS.add("sem risco");
        SPAM_KEYWORDS.add("oferta especial");
        SPAM_KEYWORDS.add("não perca essa chance");
        SPAM_KEYWORDS.add("crédito aprovado");
        SPAM_KEYWORDS.add("de graça");
        SPAM_KEYWORDS.add("sem anuidade");
        SPAM_KEYWORDS.add("contas");
        SPAM_KEYWORDS.add("cashback");
        SPAM_KEYWORDS.add("premia");
        SPAM_KEYWORDS.add("economizar");
        SPAM_KEYWORDS.add("dinheiro");
        SPAM_KEYWORDS.add("va diretamente");
        SPAM_KEYWORDS.add("acesse");
        SPAM_KEYWORDS.add("bonus");
    }

    public static boolean isSpam(String body) {
        int spamScore = 0;

        for (String keyword : SPAM_KEYWORDS) {
            if (body.toLowerCase().contains(keyword)) {
                spamScore += 5;
            }
        }

        return spamScore > 5;
    }
}
