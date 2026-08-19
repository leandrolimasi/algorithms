package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Pattern: <b>Brute-force character membership check</b>.
 *
 * <p>Despite the method name {@link TwoStrings#commonSubstring}, it does not actually look for a
 * shared <i>substring</i> (a contiguous run of characters) &mdash; it checks whether the two
 * strings share at least one <i>character</i> in common, anywhere, in any order. For each pair it
 * walks every character of the first string and does an {@code indexOf} scan of the second, so it
 * is O(n*m) per pair. A hash set of one string's characters would make each lookup O(1) instead,
 * turning the whole check into O(n+m); the tests here don't require that optimization, but it's
 * worth knowing this method's name is misleading relative to what it computes.
 *
 * <p>Learning point: always verify what an algorithm actually does against what its name/problem
 * statement claims &mdash; "common substring" and "common character" are very different problems,
 * and confusing them is an easy way to solve the wrong task correctly.
 */
public class TwoStringsTest {

  private TwoStrings twoStrings = new TwoStrings();

  @Test
  @DisplayName("First pair shares a character ('h'/'o'/'l'), second pair shares none")
  public void testCase1() {
    boolean[] result =
        twoStrings.commonSubstring(Arrays.asList("hello", "hi"), Arrays.asList("world", "bye"));
    boolean[] expected = new boolean[] {true, false};
    assertArrayEquals(result, expected);
  }

  @Test
  @DisplayName("Identical strings always share a character")
  public void identicalStringsShareAllCharacters() {
    boolean[] result = twoStrings.commonSubstring(Arrays.asList("abc"), Arrays.asList("abc"));
    assertArrayEquals(new boolean[] {true}, result);
  }

  @Test
  @DisplayName("Completely disjoint alphabets share no character")
  public void disjointAlphabetsShareNoCharacter() {
    boolean[] result = twoStrings.commonSubstring(Arrays.asList("abc"), Arrays.asList("xyz"));
    assertArrayEquals(new boolean[] {false}, result);
  }

  @Test
  @DisplayName("An empty first string can never contain a character from the second string")
  public void emptyFirstStringNeverMatches() {
    // checkContainsString loops over strA's characters; with an empty strA the loop body never
    // runs, so the result is false regardless of what strB contains.
    boolean[] result = twoStrings.commonSubstring(Arrays.asList(""), Arrays.asList("anything"));
    assertFalse(result[0]);
  }

  @Test
  @DisplayName("Large random-looking strings: sample data resembling the original problem's scale")
  public void testCase2() {
    boolean[] result =
        twoStrings.commonSubstring(
            Arrays.asList(
                "augxttdwiflnfqsjnttxwbphgvgjgtkuajymlzgvbnkxwvtlojnqbuvxqnxevsddeojzkfhdijxpylhhikdhdjezhbtpoyrrlwpcjluaiispuzhwooezhfgjhmwymlsgmbjrqfywvphtvjixrsmgnmvdsdqruxrvutdnthivxbmzrrjagmmyqttwkusamwdumzatjosswsgatdlqtjzxuzxxrdujwmrcyllobngkayzbbeuayhowqexwocikvbvpanmfufeltvltvmebvxprdhhnsfsapaieibuuunpjvtkghoanggouywbyjbaolqnrrihcvtqxqetgpkzarsqgpuwsrdxevhbmmrngoyxuulmvehatpwaenmdfceaesbbsnizdtxqcolypnyblpkwkllyccwopbzmzfftrtcqxfrxwieuodrajtuvalvwrksmukoirqgggqvvaknsfzzixhiasjpuiherpuqkccgymdbayeyfpbsnmrkqzpratahquatcysnuzszseduttcsablnbnjhtxioxsofidagkprbjubaticqousmykcyxroesflgjetmzinwpzyqjhbjskgmrbbdulctsxdkxpjpxoawacffhyjjrltcsiipzlhmdlaxyjjsmnredwhkakcmgmwrmzrniavaakavwcztkmveoesxkyiyfvagomnnjougobkorkdxblqhlxvauprkombtwhijmlrrdjzpjqgitvfvlhkgdicrjhfbzoschkommnbqzzmhcqlcznybvitolbjbpyiqvefrszqxtmigfwplxtnexrvcmmlniqgkoujkkykgndgovotnbgvlolqkomirtrkueiapyonqtmamrducfwsqhwgjhipblqsckqwjpedbldntdaclkdstvtndfhlgqoirnelm",
                "xpdeuhevbpjjaiezkrgdmsdkmrmzqfbwlmcsitcpthcbvxzqvqhuamhhapllumnqqtcgqtyweuieivcynxzzcdsbehpbikbxidpkxolvzjwmjyrxwtyiqsmqemslpmopqpwhtkyqucuxdnbpywisuyfxqdfwcbbwqwhrpbdiojfkqpdivcvmsdwrgfdfxqmwhcufwutnqzxnnsayzurrhhjavcxkcjsiariykrqcffixtcoftbkatslndrrbyaytmwzwyuqccgtndqkaaoedjyzfrfzhwmswasqzcfzvlzmwsmxqblbrjmxfugvewxzyhqxbchpeveiyibbzdnshoquvxchwmpuoejsjcwriyddwvqrdixydnvaiqhzbrwkoeentkdvktfrxueddedvcyyttwsmwlvtqmvuiqbqsrheysuloieicyxgqlxvylyburgihigbrfmxvmjrnemxkqptnmwfaqqhdxsgialqefznlizbxljggsgpacrmpqpovtggclvyzodrldzwclbgcstmptlvtiuccrncrkstryqohyotlqrcpjrafrbtadsdfewvtydicyotqixrboyupmqxbrlwppvrtroshaogjqbyeksbuaktbtaiynlfllidfxspalnqjamhmlldxufwiwdfnpbhyoqozwkrwylejohyrbldgrlmjoqsjgsvqzpyphmanhpilmdcrbqgcrmiamlhlxqjwjakonlqqrzxmvxfcjfodrwodluaaksolkxukczarajplhlokblzihybsdayyxifvjlobkvkoylytqsyoblpwwpmqxjdbwujyxipjwpsmfyugfaohscmpbzrouljrwwggdofhfrnmdrbxjeuusdacgvnhapxsiwbkaqxqrajelzlietjaqjfbjbidjbgvujbxpgcaasrxcqaemkalpaeiicllfkewie",
                "dcfwkrdbapslyhmersirjgnagrdgdjxcvkxwcvaqkluaomfymiytccxybmjpvwmxpthkmidsyqzuauxcznuylznutyyvhfjawugojzoujfrbudghrzaykjuberuhhejhvwlfylnhopyiweevmewrgcfxpkshmredyggpluwtuaeubolhkxxoendpmxsbpqxrueyeilhqznmhtwiinnfvqiidujizulzktrgstfcfqoufoljzhfdvlqwdesfefjvxfqkiwcjebdwfykxlmjbaiqdehoqjqwskbldpihvcbuiisweglqfmzoiajtlzbpjlypuszjppqngdpyadwqcnuyphlmooqwipnabjgfuunnyrqkttxefznldmagyivjulvclbulphopjviukjyexpmtmjoydpqlvnnbyeycwvycieexjvcixoynilbixzkxzswahuzckazfcfilqpkyvjyfayyxpqtqkncstiizwilheqlxaesokmivoxirzjvvkbfrwaspndqfrqxlyopjjinyzpimedsoqstrrefgleekcuevneuvapvuiejbtowjamsqszccybtotlabmubyeuqzabeitndcrpbpazjctbroopfjygridmtwwspvjxgpoetnvydzywuinnsfvzxpklzebaxjuncvtlxyzrxbhbqostfzxahucqmzmgzrgopjwtxqmwvpalssmpiwmedhllhdwstonfjrywciuvhbylqqnobbzaphkqcvmilgnqhpjkbnurwgsfrkfmfvergymamwwqobjhiftefolaofzesexikfblpxgqgmkivrtyqtluthqhgzqx"),
            Arrays.asList(
                "pyhqhnibfqxfzyfojddppodiwgncxerswvhamtdshqhwrlowwpknepgsqaqlinhprlhrqwyhxmuahrpgpaxccwhaglhtayvldimqlqubzavgepjjrcwoqpxuevzytjoioiupwinzzwubpmujadrzhwdskolfkrncrdlgndrihnbrhuawzzyxnojosovujptkrorunpdexogsycsntufbrflbckcvfnjccxlftbiwjqgaltmunhadehcxjbqobiijhftqpdnjgdoalqatwgmhqgheglfhcxepvbpmctnwnawqehvujwpditebxvmwliqqaylpyzlmwzryzfxbbsttyrhuczzcmrhfjfipnchgjsoxspudutdvkouqpwnrnrdelihsjtioayszqkptzqmsdmcsrewpxenpgymxstltijaoidhqbatiibuiuqjyncbkvepaqjmvkvudooywbhbznjcsrqqffeotvygwrjclwuqdqkzomuohrobawdbbtxxfiisbykxvkbxefwyeqovklhpeeyzoqojxogntzlhdftkpzscikrppbiuzcwkvwfnzmnswugzxwciptkcrkbdqplwokscwhejnvgywoahvzpbwjkbgryktnthsbnnqoxjrbabbedylryrijlouvkjwyxhnyefghhiypesaosrspczjawgkzdmomqhrcomydsyfwadlbkjrptmxmbtjblrohrjsylzqmcjdxeatwifwwuhzafznfepcsrmdhuysdtmpmavincpjohcscdshvnnbyiaqeyqnnzkzthyvxkmlolyhzwhktwastxrpjgpbtsbwkioexzbtexrbwsjxhnfncdbjoztaiuvzasjlrpqghpwakumypekiurjgqqoxpluzxszcmgxqwamzgkmsrwjvfnixkdokse",
                "dcbyewkqpirreeiptnfbhvrcqcxzdqztcwvraecnikflgwzucnsawlhfawpptawblyuqivtveumselhesspwnglosezhhzwdaehrqonnyfgkfykluhkpqzfhmbeiycvxhltbxdqhfnymkbeqahvtzovrerxzrwbulxnadnfqeuiygrcmcvwvxnpjulxougmtbpukjlmmdrawisrjtvrqvypfsrbdqjbrbxqhvndupusngyvoqeakzoxrtrlsbwvjqeuduebfrdynhixytkyfnluoenpedekqtbbsfwtzgohtlbeuvfzbrapezatjfzlvekocbmszgcljcffyrvlbikqftepaoytxekvcwpjkyymqenipsykyejamhyulwflsueggmlddpcgfxcvtbgcikvqfcjmodfblrepotoxvxwuthitsntjirxneneegntthflzgxgwowucnamevfqdszwublfpgzomenizotyambxlfweevfolltsgoygmsnerzpvnkxmvtuewvuafrcmwgwslhvmnoiknmsnnjhwnysapdwsbakluphnwptvhbnsftqoepqycjafchrxnpuetesowgjyvqqiezalpjvslcrqfwqznjstwocgnidhflddkqgwbpfjamhofkefvdbvjmsaqsmrrdhvtadniciramsvvzhejopqyahkqhxfubdjtpwexpqoziqacrfatlyrkajuhwftlxitbexwryzvisirzgbmgjyvytmbppnkafmzarqensvtqoupmilbbpaualanlkdqfnswqywohvddmklkrzatjbwanyrceqinxpgbnifxwtthaogtoqfjtnylwwiabwpszhvxqhqkmppjlstkdbtvntitzguygocqtfpqpuslvaamczifwsxbfzhoemeeossrrsservdrtmwwglreogedgcmebaukcwyz",
                "terdptlntydffrjwbxwjqevwqbdcenrnmiovbeaobqsmmnptrrdpddgmsfpazhvltwzkoflfevhdxwqcrxmdctmtmcaekdtxgazntwcuahesftnhsurhkbazmmcagdymnunxbmvfxtzrkqnbevlgyahitfximywsblgqgrdlljesjlncsshxsqjdkrhodknpsibttmilbusapohjgjgshvzurfeypagzlochqltitvailrfczymoqcadawvhaexqnokmbtjevsyrmjikcieksxiwfwrjhvoqegaqzqbqranadwixsoblmibyrfbhkgogmrftovwwvboonxwkmhhyypgkfvleihzysgvagcuxptwzgzaiqpgitnhddvohjtegmnjftggksaokglldmwefilkiuegndbptuxksydstzlquzlymsldeufciajitzjhhgbgeacmwsnukpdeycppmdnlpjngbweobomethqocetvttjsgmbwkpvpotxxtrtsxwwyebjudfpbyoodnvxrqqdzrqigzikhjbzgidbsijzxtysxyqnnbogykovqkjwsadijtcbclqkodaqbacaohdrowbfxojvwpmjztctrsunprxzggameiqmurelnhwtzxhjvbkdfnqmgdyeyorudxcxewgjslvdotyojynzqyppprrrlhocyetkcnhskzgbkoffzeqlfvhqbyrqwximhrjxhpvkrkxwjroohtancuxyqutfuiowhfpsepurzccgmolfkzduyruvhpgigzcsblhniaulqlhpfhhrqyqbvzpeeffddfqaiwctehpkvkmxwgpikirrge"));
    boolean[] expected = new boolean[] {true, true, true};
    assertArrayEquals(result, expected);
  }
}
