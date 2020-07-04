package com.bib.service;

import static org.junit.Assert.assertEquals;

import com.bib.BibliothekApplicationTests;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AlgorithmServiceTest extends BibliothekApplicationTests {

    @Autowired
    private AlgorithmService algorithmService;

    @Test
    public void getClosestToValueZero() {
        assertEquals(1, algorithmService.getClosestToZero(new int[]{1}));
    }

    @Test
    public void getClosestToValueZero_allPositive() {
        assertEquals(3, algorithmService.getClosestToZero(new int[]{7, 12, 54, 8, 3, 1233, 11}));
    }

    @Test
    public void getClosestToValueZero_positiveAndNegative() {
        assertEquals(2, algorithmService.getClosestToZero(new int[]{7, 12, 54, 8, -3, 2, -1233, 11, -43,}));
    }

    @Test
    public void getClosestToValueZero_positiveEqualNegative() {
        assertEquals(3, algorithmService.getClosestToZero(new int[]{7, 12, 54, 3, -3, -1233, 11, -43,}));
    }

    @Test
    public void getClosestToValueZero_negativeEqualNegative() {
        assertEquals(-3, algorithmService.getClosestToZero(new int[]{7, 12, 54, -3, -3, -1233, 11, -43,}));
    }

    @Test
    public void mimeType_success() throws IOException {
        String[] mimeExpected = algorithmService.getMimeExpected();
        String[] mimeResult = algorithmService.makeupFileNamesWithMimeTypes();
        IntStream.range(0, mimeExpected.length).forEach(i -> assertEquals(mimeExpected[i], mimeResult[i]));
    }

    @Test
    public void quickSort() {
        int[] toBeSorted = {9, 6, 7, 4, 2, 5, 1, 3, 0, 8, 32, 76, 98, 34, 876, 5, 1, 398, 78, 27, 85, 39, 20, 45, 2876};
        System.out.println(Arrays.toString(toBeSorted));
        algorithmService.quickSort(toBeSorted, 0, toBeSorted.length - 1);

        System.out.println(Arrays.toString(toBeSorted));
        int[] expected = {0, 1, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 20, 27, 32, 34, 39, 45, 76, 78, 85, 98, 398, 876, 2876};
        assertEquals(Arrays.toString(expected), Arrays.toString(toBeSorted));
    }

    @Test
    public void sumOfCharacterIndex() {
        assertEquals(21, algorithmService.sumOfCharacterIndex("pelicannn", "n"));
    }

    @Test
    public void sumOfCharacterIndex_Expect52() {
        assertEquals(52, algorithmService.sumOfCharacterIndex("Where's your crown King Nothing?", "g"));
    }

    @Test
    public void calculateRectangles_Simple() {
        assertEquals(4, algorithmService.calculateRectangles(new int[]{0, 2, 5, 10}, new int[]{0, 3, 5}));
    }

    @Test
    public void calculateRectangles_Square() {
        assertEquals(14, algorithmService.calculateRectangles(new int[]{0, 3, 6, 9}, new int[]{0, 3, 6, 9}));
    }

    @Test
    public void calculateRectangles_SquareLength5() {
        assertEquals(30, algorithmService.calculateRectangles(new int[]{0, 3, 6, 9, 12}, new int[]{0, 3, 6, 9, 12}));
    }

    @Test
    public void calculateRectangles_Big() {
        int[] xAxis = new int[]{0, 11, 25, 26, 29, 30, 40, 44, 56, 65, 71, 87, 98, 100, 108, 130, 149, 153, 161, 173,
                179, 200};
        int[] yAxis = new int[]{0, 1, 11, 16, 17, 19, 37, 38, 53, 65, 69, 100};
        int sum = Arrays.stream(xAxis).sum();
        assertEquals(123, algorithmService.calculateRectangles(xAxis, yAxis));
    }

    @Test
    public void calculateRectangles_BigV() {
        int[] xAxis = new int[]{0, 90, 117, 306, 744, 954, 1005, 1327, 1604, 2099, 2167, 2244, 2272, 2350, 2466, 2758,
                2816, 2829, 2860, 2963, 3040, 3202, 3265, 3298, 3600, 4055, 4158, 4159, 4272, 4308, 4325, 4382, 4595,
                4857, 4894, 4953, 4955, 5091, 5145, 5193, 5797, 5812, 5839, 5899, 5912, 5963, 6044, 6142, 6161, 6703,
                6802, 6869, 6950, 7025, 7028, 7217, 7669, 7748, 7804, 8018, 8114, 8118, 8200, 8203, 8422, 8683, 8692,
                8697, 8711, 8806, 8839, 8927, 9006, 9189, 9266, 9382, 9391, 9516, 9534, 9630, 9764, 9791, 9853, 9881,
                10088, 10121, 10271, 10317, 10327, 10550, 10559, 10624, 10944, 11005, 11135, 11212, 11331, 11393, 11449,
                11490, 11764, 11804, 11875, 11909, 12011, 12045, 12091, 12159, 12398, 12453, 12559, 12621, 12693, 13144,
                13196, 13205, 13262, 13275, 13323, 13538, 13608, 14153, 14291, 14346, 14518, 14520, 14560, 14689, 14765,
                14957, 15042, 15082, 15382, 15477, 15539, 15586, 15612, 15630, 15719, 15805, 16101, 16142, 16300, 16379,
                16563, 16580, 16685, 16774, 16919, 17010, 17056, 17470, 17482, 17512, 17520, 17709, 17722, 17782, 17800,
                17829, 17945, 18048, 18071, 18181, 18279, 18330, 18492, 18554, 18653, 18906, 18938, 19279, 19294, 19339,
                19587, 19590, 19672, 19695, 19730, 19854, 19950, 20000};
        int[] yAxis = new int[]{0, 90, 177, 485, 652, 664, 793, 797, 943, 1151, 1271, 1506, 1569, 1650, 1789, 1906,
                2037, 2077, 2221, 2241, 2323, 2354, 2533, 2588, 2858, 3039, 3223, 3360, 3480, 3483, 3587, 3635, 3842,
                3919, 3962, 4356, 4410, 4563, 4949, 4997, 5059, 5074, 5090, 5175, 5187, 5256, 5309, 5354, 5384, 5848,
                5995, 6374, 6506, 6516, 6547, 6563, 6628, 6632, 6709, 6910, 6916, 6947, 6983, 7203, 7345, 7602, 7800,
                7844, 7858, 7955, 8206, 8213, 8317, 8400, 8435, 8488, 8538, 8580, 8710, 8813, 8815, 8887, 8896, 8913,
                9111, 9124, 9209, 9317, 9345, 9350, 9544, 9593, 9945, 10125, 10208, 10225, 10270, 10522, 10993, 11014,
                11025, 11118, 11231, 11481, 11538, 11742, 11761, 11856, 11887, 12078, 12112, 12325, 12506, 12601, 12617,
                12620, 12719, 12763, 12875, 12917, 13257, 13403, 13485, 13488, 13635, 13727, 13814, 13863, 13882, 13921,
                13996, 14027, 14068, 14139, 14338, 14410, 14526, 14563, 14617, 14620, 14691, 14763, 14786, 14978, 15215,
                15730, 15776, 15941, 15964, 16094, 16174, 16182, 16303, 16309, 16450, 16458, 16649, 16703, 17008, 17143,
                17220, 17330, 17628, 17877, 18203, 18390, 18463, 18486, 18611, 18692, 18693, 18729, 18902, 18978, 19219,
                19299, 19355, 19373, 19506, 19520, 19599, 20000};
        assertEquals(18431, algorithmService.calculateRectangles(xAxis, yAxis));
    }

    @Test
    public void calculateRectangles_HighDensity() {
        int[] xAxis = new int[]{0, 1, 14, 79, 126, 390, 506, 573, 690, 747, 778, 798, 887, 896, 907, 912, 1026, 1122,
                1172, 1193, 1223, 1380, 1557, 1693, 1759, 1840, 2050, 2063, 2279, 2321, 2332, 2396, 2468, 2514, 2664,
                2803, 2813, 2823, 2886, 2913, 3131, 3363, 3426, 3580, 3583, 3780, 3979, 3981, 4014, 4102, 4198, 4284,
                4355, 4666, 4693, 4708, 4718, 4926, 5055, 5094, 5121, 5292, 5298, 5299, 5372, 5406, 5532, 5634, 5800,
                5801, 5835, 5838, 5851, 5875, 6183, 6305, 6358, 6359, 6433, 6479, 6589, 6600, 6609, 6635, 6766, 6774,
                6906, 6940, 6957, 6978, 7004, 7008, 7086, 7226, 7413, 7442, 7525, 7536, 7573, 7625, 7693, 7721, 8004,
                8113, 8252, 8300, 8303, 8385, 8390, 8523, 8526, 8688, 8733, 8766, 9027, 9075, 9170, 9196, 9198, 9223,
                9331, 9497, 9910, 9945, 10000};
        int[] yAxis = new int[]{0, 59, 60, 63, 128, 132, 146, 183, 249, 270, 303, 380, 387, 467, 606, 643, 663, 682,
                707, 728, 734, 799, 851, 1019, 1078, 1105, 1116, 1122, 1126, 1167, 1237, 1327, 1402, 1436, 1439, 1635,
                1674, 1718, 1751, 1817, 1864, 1865, 1871, 1921, 1995, 2001, 2085, 2089, 2214, 2274, 2282, 2399, 2442,
                2443, 2447, 2564, 2569, 2577, 2875, 2937, 2988, 2992, 3017, 3121, 3162, 3202, 3216, 3226, 3228, 3259,
                3416, 3426, 3443, 3475, 3795, 3852, 3879, 3920, 3993, 4038, 4039, 4052, 4137, 4207, 4243, 4248, 4251,
                4270, 4328, 4346, 4354, 4369, 4379, 4389, 4396, 4413, 4426, 4477, 4491, 4501, 4508, 4531, 4533, 4544,
                4659, 4666, 4720, 4769, 4846, 4877, 4977, 5036, 5143, 5254, 5348, 5416, 5425, 5452, 5468, 5523, 5580,
                5601, 5611, 5613, 5620, 5714, 5722, 5724, 5748, 5759, 5794, 5803, 5927, 5979, 6042, 6101, 6115, 6168,
                6177, 6184, 6230, 6269, 6336, 6343, 6416, 6428, 6709, 6756, 6786, 6813, 6895, 6919, 6945, 6971, 7099,
                7114, 7142, 7179, 7241, 7267, 7381, 7401, 7412, 7416, 7422, 7427, 7456, 7514, 7519, 7533, 7555, 7577,
                7597, 7735, 7739, 7745, 7777, 7780, 7859, 7913, 7917, 7948, 7984, 7999, 8000, 8035, 8051, 8071, 8143,
                8155, 8228, 8392, 8480, 8490, 8575, 8653, 8677, 8731, 8754, 8830, 8867, 8887, 9000};
        assertEquals(22281, algorithmService.calculateRectangles(xAxis, yAxis));
    }

    @Test
    public void calculateRectangles_LowDensity() {
        int[] xAxis = new int[]{0, 8393, 13715, 13752, 20000};
        int[] yAxis = new int[]{0, 7017, 8582, 16500, 19518, 19990};
        assertEquals(0, algorithmService.calculateRectangles(xAxis, yAxis));
    }

    @Test
    public void calculateRectangles_Vertical() {
        int[] xAxis = new int[]{0, 4217, 9527, 18150, 18400, 20000};
        int[] yAxis = new int[]{0, 221, 239, 328, 499, 652, 688, 764, 768, 823, 910, 1133, 1150, 1151, 1222, 1268, 1319,
                1512, 1666, 2002, 2017, 2022, 2098, 2217, 2329, 2395, 2464, 2492, 2515, 2628, 2800, 2873, 2929, 3026,
                3030, 3063, 3211, 3253, 3266, 3330, 3364, 3396, 3402, 3456, 3463, 3588, 3681, 3779, 3829, 3853, 3907,
                3975, 3981, 4061, 4110, 4112, 4189, 4264, 4345, 4369, 4466, 4558, 4633, 4685, 4802, 4818, 4985, 5018,
                5022, 5088, 5136, 5241, 5264, 5302, 5310, 5325, 5340, 5462, 5480, 5592, 5632, 5660, 5692, 5785, 5913,
                5932, 6066, 6149, 6397, 6411, 6419, 6524, 6563, 6790, 6811, 6830, 6890, 6924, 6939, 7009, 7104, 7140,
                7258, 7302, 7377, 7408, 7449, 7800, 7850, 7871, 7940, 8022, 8221, 8235, 8281, 8305, 8338, 8399, 8530,
                8703, 8870, 8902, 8927, 8955, 8977, 9037, 9055, 9066, 9109, 9133, 9288, 9293, 9425, 9553, 9590, 9724,
                9770, 9781, 9804, 9823, 9859, 9933, 9980, 9981, 10059, 10179, 10206, 10211, 10290, 10378, 10613, 10680,
                10699, 10731, 10734, 10806, 10888, 10946, 10956, 11006, 11025, 11058, 11228, 11349, 11351, 11362, 11442,
                11522, 11525, 11552, 11650, 11659, 11683, 11707, 12001, 12007, 12052, 12120, 12151, 12174, 12411, 12419,
                12473, 12574, 12662, 12757, 12766, 12769, 12771, 12790, 12809, 12826, 12891, 12893, 12928, 12941, 13001,
                13012, 13267, 13350, 13446, 13555, 13659, 13790, 13796, 13808, 13828, 13904, 13949, 14016, 14040, 14070,
                14099, 14122, 14327, 14329, 14374, 14424, 14602, 14624, 14635, 14798, 14805, 14865, 14945, 15039, 15140,
                15144, 15306, 15308, 15312, 15363, 15436, 15465, 15606, 15640, 15666, 15692, 15810, 15870, 16028, 16031,
                16169, 16255, 16256, 16337, 16398, 16430, 16435, 16452, 16483, 16487, 16526, 16598, 16603, 16647, 16652,
                16757, 16843, 16852, 16859, 17108, 17193, 17227, 17365, 17502, 17677, 17694, 17702, 17749, 17939, 18041,
                18159, 18171, 18200, 18278, 18356, 18362, 18466, 18475, 18600, 18752, 18757, 18814, 18832, 18878, 18924,
                18996, 19009, 19038, 19107, 19199, 19226, 19291, 19394, 19396, 19432, 19599, 19727, 19810, 19989,
                20000};
        assertEquals(42, algorithmService.calculateRectangles(xAxis, yAxis));
    }

    @Test
    public void enigmaEncode() {
        assertEquals("KQF", algorithmService.enigmaEncode("AAA", 4, getRotors()));
    }

    @Test
    public void enigmaEncode_2() {
        assertEquals(
                "ALWAURKQEQQWLRAWZHUYKVN",
                algorithmService.enigmaEncode("WEATHERREPORTWINDYTODAY", 7, getRotors()));
    }

    @Test
    public void enigmaEncode_3() {
        assertEquals(
                "PQSACVVTOISXFXCIAMQEM",
                algorithmService.enigmaEncode("EVERYONEISWELCOMEHERE", 9, getRotors()));
    }

    @Test
    public void enigmaEncode_4() {
        assertEquals(
                "PQSACVVTOISXFXCIAMQEMDZIXFJJSTQIENEFQXVZYV",
                algorithmService.enigmaEncode("EVERYONEISWELCOMEHEREEVERYONEISWELCOMEHERE", 9, getRotors()));
    }

    @Test
    public void enigmaEncode_5() {
        String expected =
                "ILHCSUKHTBTHIRAYSVLUAWAWXTILZPSAWHMDBQCHVHVGIDAWPDPOILZFEKQUVDEOEOYHDRPAQTBDUVMZNYEQHUSNWAMZA"
                        + "MNOAUTISRXVNOLYSABFDXAKJCOKIPFDLRBUTPSZCGSAYUBMOJXZOUNRAPJFRGQZWSLUSDBFMTKIBNBNRWMVDFUBQASZLYXTOAEMOU"
                        + "KVPDGRVKEPGIEXNBWCHLGFMXJEJKBZWPKEDVHLUWCTNUSKBRHGBZCWAZDRBKWTMLGUCONLUZZDVEOKQXLYRXAGCFTKOCIRPJEYTMK"
                        + "JQQJPAYOQAUAWTLIODLNIXORHEDBIPBRZYRMYYXABKVCRYYHJNFMTOCVXAWFVLKEVYSERNNJNEKSBEDSILXMDQGADYAMAELCBLIAS"
                        + "GNEAIYPCZZQCAGJIBHWEOIRIRQMNUEOHADDGNCUDSSMGWYTLZESSGNVXEOLWVIIVUDEKIDUDYDZIBQGQJDIZANBSNCUUTFIDUGMZNYX";
        String plaintext =
                "DENBEGRIFFDERWISSENSCHAFTODERDERWISSENSCHAFTLICHKEITDERWISSENSCHAFTALSODESSENWASSEITJEALSLOG"
                        + "ILEBESTIMMTWURDEERISTIMMEREINPHILOSOPHISCHERBEGRIFFGEWESENWENNAUCHDIEWISSENSCHAFTLICHEPRAXISDENIMPERI"
                        + "ALISMUSDESLOGOSUNABLAESSIGBEKAEMPFTHATINDEMSIESICHBEISPIELSWEISEIMMERSCHONUNDIMMERSTAERKERAUFDIENICHTPH"
                        + "ONETISCHESCHRIFTBESANNDIESESUBVERSIONWARZWEIFELLOSSEITJEINJENESALLOKUTORISCHESYSTEMMITEINBEZOGENDASDE"
                        + "NENTWURFEINERWISSENSCHAFTUNDDIEKONVENTIONENEINERJEDENNICHTPHONETISCHENCHARAKTERISTIKHATENTSTEHENLASSEN";
        assertEquals(expected, algorithmService.enigmaEncode(plaintext, 7, getRotors()));
    }

    private String[] getRotors() {
        String[] rotors = new String[3];
        rotors[0] = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
        rotors[1] = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
        rotors[2] = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        return rotors;
    }

    @Test
    public void enigmaDecode() {
        assertEquals("AAA", algorithmService.enigmaDecode("KQF", 4, getReversRotors()));
    }

    @Test
    public void enigmaDecode_1() {
        assertEquals(
                "EVERYONEISWELCOMEHERE",
                algorithmService.enigmaDecode("PQSACVVTOISXFXCIAMQEM", 9, getReversRotors()));
    }

    @Test
    public void enigmaDecode_2() {
        assertEquals(
                "THEQUICKBROWNFOXJUMPSOVERALAZYSPHINXOFBLACKQUARTZ",
                algorithmService.enigmaDecode(
                        "XPCXAUPHYQALKJMGKRWPGYHFTKRFFFNOUTZCABUAEHQLGXREZ", 5, getReversRotors()));
    }

    @Test
    public void enigmaDecode_3() {
        String expected =
                "DENBEGRIFFDERWISSENSCHAFTODERDERWISSENSCHAFTLICHKEITDERWISSENSCHAFTALSODESSENWASSEITJEALSLOG"
                        + "ILEBESTIMMTWURDEERISTIMMEREINPHILOSOPHISCHERBEGRIFFGEWESENWENNAUCHDIEWISSENSCHAFTLICHEPRAXISDENIMPERI"
                        + "ALISMUSDESLOGOSUNABLAESSIGBEKAEMPFTHATINDEMSIESICHBEISPIELSWEISEIMMERSCHONUNDIMMERSTAERKERAUFDIENICHTPH"
                        + "ONETISCHESCHRIFTBESANNDIESESUBVERSIONWARZWEIFELLOSSEITJEINJENESALLOKUTORISCHESYSTEMMITEINBEZOGENDASDE"
                        + "NENTWURFEINERWISSENSCHAFTUNDDIEKONVENTIONENEINERJEDENNICHTPHONETISCHENCHARAKTERISTIKHATENTSTEHENLASSEN";
        String chifertext =
                "ILHCSUKHTBTHIRAYSVLUAWAWXTILZPSAWHMDBQCHVHVGIDAWPDPOILZFEKQUVDEOEOYHDRPAQTBDUVMZNYEQHUSNWAMZA"
                        + "MNOAUTISRXVNOLYSABFDXAKJCOKIPFDLRBUTPSZCGSAYUBMOJXZOUNRAPJFRGQZWSLUSDBFMTKIBNBNRWMVDFUBQASZLYXTOAEMOU"
                        + "KVPDGRVKEPGIEXNBWCHLGFMXJEJKBZWPKEDVHLUWCTNUSKBRHGBZCWAZDRBKWTMLGUCONLUZZDVEOKQXLYRXAGCFTKOCIRPJEYTMK"
                        + "JQQJPAYOQAUAWTLIODLNIXORHEDBIPBRZYRMYYXABKVCRYYHJNFMTOCVXAWFVLKEVYSERNNJNEKSBEDSILXMDQGADYAMAELCBLIAS"
                        + "GNEAIYPCZZQCAGJIBHWEOIRIRQMNUEOHADDGNCUDSSMGWYTLZESSGNVXEOLWVIIVUDEKIDUDYDZIBQGQJDIZANBSNCUUTFIDUGMZNYX";
        assertEquals(expected, algorithmService.enigmaDecode(chifertext, 7, getReversRotors()));
    }

    private String[] getReversRotors() {
        String[] rotors = new String[3];
        rotors[0] = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
        rotors[1] = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
        rotors[2] = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
        return rotors;
    }
}

