package Unit2;

import java.util.Random;

public class RandomSentences {

	private static Random random = new Random();

	private static String[] conjunctions = {"and", "or", "but", "because"};
	private static String[] properNouns = {"Fred", "Jane", "Richard Nixon", "Miss America"};
	private static String[] commonNouns = {"man", "woman", "fish", "elephant", "unicorn"};
	private static String[] determiners = {"a", "the", "every", "some"};
	private static String[] adjectives = {"big", "tiny", "pretty", "bald"};
	private static String[] intransitiveVerbs = {"runs", "jumps", "talks", "sleeps"};
	private static String[] transitiveVerbs = {"loves", "hates", "sees", "knows", "looks for", "finds"};

	public static void main(String[] args) {
		String sentence = generateSentence();
		System.out.println(sentence);
	}

	private static String generateSentence() {
		String sentence = "";
		sentence +=  generateSimpleSentence();

		if (random.nextBoolean()) {
			sentence += " ";
			sentence += generateConjunction();
			sentence += " ";
			sentence += generateSentence();
		}

		return sentence;
	}

	private static String generateSimpleSentence() {
		String simpleSentence = "";
		simpleSentence += generateNounPhrase();
		simpleSentence += " ";
		simpleSentence += generateVerbPhrase();
		return simpleSentence;
	}

	private static String generateNounPhrase() {
		String nounPhrase = "";
		if (random.nextBoolean()) {
			nounPhrase += generateProperNoun();
		} else {
			nounPhrase += generateDeterminer();
			if (random.nextBoolean()) {
				nounPhrase += " ";
				nounPhrase += generateAdjective();
			}
			nounPhrase += ". ";
			nounPhrase += generateCommonNoun();
			if (random.nextBoolean()) {
				nounPhrase += " who ";
				nounPhrase += generateVerbPhrase();
			}
		}
		return nounPhrase;
	}

	private static String generateVerbPhrase() {
		String stringBuilt = "";
		int choice = random.nextInt(4);
		switch (choice) {
		case 0:
			stringBuilt += generateIntransitiveVerb();
			break;
		case 1:
			stringBuilt += generateTransitiveVerb();
			stringBuilt += " ";
			stringBuilt += generateNounPhrase();
			break;
		case 2:
			stringBuilt += "is ";
			stringBuilt += generateAdjective();
			break;
		case 3:
			stringBuilt += "believes that ";
			stringBuilt += generateSimpleSentence();
			break;
		}
		return stringBuilt;
	}

	private static String generateConjunction() {
		return conjunctions[random.nextInt(conjunctions.length)];
	}

	private static String generateProperNoun() {
		return properNouns[random.nextInt(properNouns.length)];
	}

	private static String generateCommonNoun() {
		return commonNouns[random.nextInt(commonNouns.length)];
	}

	private static String generateDeterminer() {
		return determiners[random.nextInt(determiners.length)];
	}

	private static String generateAdjective() {
		return adjectives[random.nextInt(adjectives.length)];
	}

	private static String generateIntransitiveVerb() {
		return intransitiveVerbs[random.nextInt(intransitiveVerbs.length)];
	}

	private static String generateTransitiveVerb() {
		return transitiveVerbs[random.nextInt(transitiveVerbs.length)];
	}
}