package dev.zprestige.prestige.render;

import java.util.ArrayList;
import java.util.Vector;

public class BufferBuilder implements VertexSink {
    private long a;
    private int[] b;
    private VertexSink c;
    private long d;
    private long[] j;
    private static long[] k = new long[64];
    public static int[] e;
    private static ArrayList f;
    private static Vector m;
    private static int i;
    private static Object o;
    private static int h = 52;
    private static int l = 128;
    private static int n = 17;

    public static Object g() {
        return o;
    }

    public static VertexSink a(long var0, long var2, Object var4) {
        RenderHelper33.a(var0 > 0L);
        VertexSink var5 = d(var0);
        VertexSink var6 = d(var2);
        VertexSink var7 = RenderHelper33.a(var5, var6);
        if (var4 != null) {
            m.add(var4);
        }

        return var7;
    }

    public static VertexSink c(long var0) {
        int var2 = (int)a(var0, h, 63, e, k);
        if (var2 < i) {
            return (VertexSink)f.get(var2);
        } else {
            if (f.size() % l == 0) {
                e = (int[])e.clone();
            }

            BufferBuilder var3 = new BufferBuilder(var0);
            f.add(var3);
            return var3;
        }
    }

    private static VertexSink d(long var0) {
        return new BufferBuilder(var0);
    }

    public static void a(RenderHelper33 var0) {
        i = f.size();
        c();
        var0.d();
    }

    public static void b(RenderHelper33 var0) {
        c();
        int[] var10000 = e = new int[64];
        var10000[0] = -42;
        var10000[1] = -29;
        var10000[2] = -9;
        var10000[3] = -29;
        var10000[4] = -20;
        var10000[5] = -12;
        var10000[6] = -40;
        var10000[7] = -21;
        var10000[8] = -43;
        var10000[9] = -38;
        var10000[10] = -33;
        var10000[11] = 9;
        var10000[12] = -41;
        var10000[13] = -47;
        var10000[14] = -15;
        var10000[15] = -5;
        var10000[16] = -42;
        var10000[17] = 12;
        var10000[18] = -38;
        var10000[19] = -18;
        var10000[20] = 5;
        var10000[21] = -40;
        var10000[22] = -33;
        var10000[23] = -4;
        var10000[24] = 20;
        var10000[25] = -23;
        var10000[26] = -9;
        var10000[27] = 4;
        var10000[28] = 21;
        var10000[29] = 15;
        var10000[30] = 29;
        var10000[31] = -31;
        var10000[32] = 29;
        var10000[33] = -17;
        var10000[34] = -15;
        var10000[35] = 9;
        var10000[36] = -5;
        var10000[37] = 18;
        var10000[38] = -25;
        var10000[39] = -20;
        var10000[40] = -5;
        var10000[41] = 5;
        var10000[42] = 42;
        var10000[43] = 33;
        var10000[44] = -13;
        var10000[45] = 5;
        var10000[46] = 40;
        var10000[47] = 38;
        var10000[48] = 23;
        var10000[49] = 15;
        var10000[50] = 17;
        var10000[51] = 43;
        var10000[52] = -2;
        var10000[53] = 41;
        var10000[54] = 2;
        var10000[55] = 33;
        var10000[56] = 38;
        var10000[57] = 13;
        var10000[58] = 42;
        var10000[59] = 20;
        var10000[60] = 47;
        var10000[61] = 40;
        var10000[62] = 31;
        var10000[63] = 25;
        var0.c();
    }

    private BufferBuilder(long var1) {
        this.a = var1;
        this.b = e;
        this.j = k;
    }

    @Override
    public long a(long var1) {
        long var3 = this.a(8, 55);
        long var5 = this.a ^ var1 ^ this.d;
        this.a = var5;
        return var3;
    }

    @Override
    public void b(long var1) {
        this.d = var1;
    }

    @Override
    public void a(VertexSink var1) {
        if (this != var1) {
            if (this.c == null) {
                this.c = var1;
            } else {
                this.c.a(var1);
            }
        }
    }

    @Override
    public int hashCode() {
        return (int)this.a(8);
    }

    @Override
    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else {
            return var1 instanceof BufferBuilder ? this.a(56) == ((BufferBuilder)var1).a(56) : false;
        }
    }

    @Override
    public boolean b(VertexSink var1) {
        if (this == var1) {
            return true;
        } else {
            return var1 instanceof BufferBuilder ? this.a(56, 63) - ((BufferBuilder)var1).a(56, 63) <= 0L : true;
        }
    }

    @Override
    public int[] b() {
        return this.b;
    }

    private long a(int var1) {
        return this.a(0, var1 - 1);
    }

    private long a(int var1, int var2) {
        return a(this.a, var1, var2, this.b, this.j);
    }

    private static long a(long var0, int var2, int var3, int[] var4, long[] var5) {
        long var6 = 0L;
        int var8 = var4.length;

        for (int var9 = 0; var9 < var8; var9++) {
            long var10 = var0 & k[var9];
            long var12 = (long)var4[var9];
            if (var10 != 0L) {
                if (var12 > 0) {
                    var10 >>>= var12;
                } else if (var12 < 0) {
                    var10 <<= ~var12 + 1;
                }

                var6 |= var10;
            }
        }

        long var13 = var6;
        int var11 = 63 - var3;
        if (var11 > 0) {
            var13 = var6 << var11;
        }

        long var14 = (long)(var2 + 64 - 1 - var3);
        if (var14 > 0) {
            var13 >>>= var14;
        }

        return var13;
    }

    private static void c() {
        a(0, f.size() - 1, f, new ArrayList(f), 0);
    }

    private static void a(int var0, int var1, ArrayList var2, ArrayList var3, int var4) {
        if (var0 < var1) {
            int var5 = var0 + (var1 - var0) / 2;
            if (++var4 < n) {
                a(var0, var5, var2, var3, var4);
                a(var5 + 1, var1, var2, var3, var4);
            }

            a(var0, var5, var1, var2, var3);
        }
    }

    private static void a(int var0, int var1, int var2, ArrayList var3, ArrayList var4) {
        int var5 = var0;
        int var6 = var1 + 1;

        for (int var7 = var0; var7 <= var2; var7++) {
            var4.set(var7, var3.get(var7));
        }

        while (var5 <= var1 && var6 <= var2) {
            VertexSink var8;
            if (((VertexSink)var4.get(var5)).b((VertexSink)var4.get(var6))) {
                var8 = (VertexSink)var4.get(var5++);
            } else {
                var8 = (VertexSink)var4.get(var6++);
            }

            var3.set(var0, var8);
            var0++;
        }

        while (var5 <= var1) {
            var3.set(var0, var4.get(var5));
            var0++;
            var5++;
        }
    }

    static {
        long var0 = 1L;

        for (int var2 = 0; var2 < 64; var2++) {
            k[var2] = var0;
            var0 <<= 1;
        }

        o = new Object();
        m = new Vector();
        f = new ArrayList();
        a0();
        i = f.size();
        c();
    }

    private static void a0() {
        e = new int[]{
            -33,
            -55,
            -25,
            -8,
            -43,
            -25,
            -1,
            1,
            -1,
            1,
            -24,
            8,
            -34,
            -36,
            -21,
            -1,
            1,
            -36,
            -18,
            -33,
            -35,
            -22,
            -38,
            -25,
            -30,
            -38,
            -6,
            25,
            -13,
            -32,
            25,
            -28,
            6,
            33,
            24,
            21,
            18,
            -3,
            -24,
            -6,
            3,
            13,
            -8,
            22,
            -13,
            6,
            34,
            43,
            25,
            36,
            8,
            -7,
            33,
            36,
            30,
            35,
            55,
            13,
            7,
            28,
            38,
            32,
            24,
            38
        };
        f.add(new BufferBuilder(-7649176990319478997L));
        f.add(new BufferBuilder(6021278898969724677L));
        f.add(new BufferBuilder(7115027366131081075L));
        f.add(new BufferBuilder(-7651428517436295381L));
        f.add(new BufferBuilder(6062377975152644802L));
        f.add(new BufferBuilder(2243170933948983133L));
        f.add(new BufferBuilder(-4094356763992397963L));
        f.add(new BufferBuilder(-1107148554846165954L));
        f.add(new BufferBuilder(-1286430344283762525L));
        f.add(new BufferBuilder(-1831038926418664309L));
        f.add(new BufferBuilder(-6354719162614405529L));
        f.add(new BufferBuilder(-8506449974601101173L));
        f.add(new BufferBuilder(8633968285251542579L));
        f.add(new BufferBuilder(-6249007606196702135L));
        f.add(new BufferBuilder(-7188844375264876873L));
        f.add(new BufferBuilder(-4345092557189540506L));
        f.add(new BufferBuilder(-2632335675495490175L));
        f.add(new BufferBuilder(-1243545347136972287L));
        f.add(new BufferBuilder(-3523611573846561655L));
        f.add(new BufferBuilder(6935692940177033288L));
        f.add(new BufferBuilder(-4858100291223264181L));
        f.add(new BufferBuilder(-2450504512435145929L));
        f.add(new BufferBuilder(713245897628964038L));
        f.add(new BufferBuilder(-470162520600120837L));
        f.add(new BufferBuilder(-7261871967915938009L));
        f.add(new BufferBuilder(9112052544143724587L));
        f.add(new BufferBuilder(-741517486873938593L));
        f.add(new BufferBuilder(8274905796648157430L));
        f.add(new BufferBuilder(8182818563061477568L));
        f.add(new BufferBuilder(683098719807329197L));
        f.add(new BufferBuilder(-1931611121395223946L));
        f.add(new BufferBuilder(-2936086504366924589L));
        f.add(new BufferBuilder(8508012099847734425L));
        f.add(new BufferBuilder(-3051339683445750871L));
        f.add(new BufferBuilder(7823862609310475825L));
        f.add(new BufferBuilder(-630333041359874273L));
        f.add(new BufferBuilder(-4294871533233534140L));
        f.add(new BufferBuilder(1665642233147838456L));
        f.add(new BufferBuilder(-914499574569628890L));
        f.add(new BufferBuilder(-8587818986200196448L));
        f.add(new BufferBuilder(802182560945210048L));
        f.add(new BufferBuilder(8117652667534771398L));
        f.add(new BufferBuilder(5102926607849366974L));
        f.add(new BufferBuilder(-4642530281704379163L));
        f.add(new BufferBuilder(-2314903740319445028L));
        f.add(new BufferBuilder(-8196762515845565991L));
        f.add(new BufferBuilder(488425702816181875L));
        f.add(new BufferBuilder(-7744709769461338676L));
        f.add(new BufferBuilder(-1384914714294381625L));
        f.add(new BufferBuilder(6376193074664079881L));
        f.add(new BufferBuilder(7655649855320857536L));
        f.add(new BufferBuilder(-6040061248367809216L));
        f.add(new BufferBuilder(-8070919431904585849L));
        f.add(new BufferBuilder(-4917252096074680760L));
        f.add(new BufferBuilder(-3778892574057899659L));
        f.add(new BufferBuilder(3156297505560575669L));
        f.add(new BufferBuilder(-481657315393033657L));
        f.add(new BufferBuilder(-5834579944880358322L));
        f.add(new BufferBuilder(-6733363871950849573L));
        f.add(new BufferBuilder(1900324777150013938L));
        f.add(new BufferBuilder(-98767447440803693L));
        f.add(new BufferBuilder(4984646333241370078L));
        f.add(new BufferBuilder(1386049793193599528L));
        f.add(new BufferBuilder(6546454295836122586L));
        e = (int[])e.clone();
        f.add(new BufferBuilder(598505703703054076L));
        f.add(new BufferBuilder(-6788878158731728931L));
        f.add(new BufferBuilder(-7615399728562849056L));
        f.add(new BufferBuilder(5412278113605174787L));
        f.add(new BufferBuilder(3876012969123456412L));
        f.add(new BufferBuilder(2556530039102354286L));
        f.add(new BufferBuilder(7114219646074815386L));
        f.add(new BufferBuilder(1319545010852995849L));
        f.add(new BufferBuilder(-3181654918938933361L));
        f.add(new BufferBuilder(-8040568353090958328L));
        f.add(new BufferBuilder(436836193471832771L));
        f.add(new BufferBuilder(1801225361431303141L));
        f.add(new BufferBuilder(-8527082700926010378L));
        f.add(new BufferBuilder(7105411707124131576L));
        f.add(new BufferBuilder(-2401476458321484200L));
        f.add(new BufferBuilder(4698931183202614429L));
        f.add(new BufferBuilder(939920379903731653L));
        f.add(new BufferBuilder(2850135864196478584L));
        f.add(new BufferBuilder(7950866530573663355L));
        f.add(new BufferBuilder(1624806897574438667L));
        f.add(new BufferBuilder(2984381551225378849L));
        f.add(new BufferBuilder(-6525414702637831360L));
        f.add(new BufferBuilder(7856637914043130739L));
        f.add(new BufferBuilder(-71219325941204838L));
        f.add(new BufferBuilder(-3423773354536227450L));
        f.add(new BufferBuilder(4038897098582724368L));
        f.add(new BufferBuilder(-5481139332747355726L));
        f.add(new BufferBuilder(-7268684146465480007L));
        f.add(new BufferBuilder(-424467516851135237L));
        f.add(new BufferBuilder(-6163682273045483688L));
        f.add(new BufferBuilder(-829591249395319264L));
        f.add(new BufferBuilder(-6552711216695385862L));
        f.add(new BufferBuilder(-4010836276203184759L));
        f.add(new BufferBuilder(1912939921244024221L));
        f.add(new BufferBuilder(1464061502064492756L));
        f.add(new BufferBuilder(7211103416390488433L));
        f.add(new BufferBuilder(-6812485095391364001L));
        f.add(new BufferBuilder(7516102967173829627L));
        f.add(new BufferBuilder(2253214245848895883L));
        f.add(new BufferBuilder(3501659090213013028L));
        f.add(new BufferBuilder(-4955735216145303780L));
        f.add(new BufferBuilder(4668281060420621632L));
        f.add(new BufferBuilder(4559816488748107966L));
        f.add(new BufferBuilder(-583459211740016166L));
        f.add(new BufferBuilder(-5601445603293499194L));
        f.add(new BufferBuilder(6728168624509042648L));
        f.add(new BufferBuilder(-2200880934729577809L));
        f.add(new BufferBuilder(314220719538636150L));
        f.add(new BufferBuilder(7585486904458493985L));
        f.add(new BufferBuilder(7160671427046692518L));
        f.add(new BufferBuilder(-2548128555449861275L));
        f.add(new BufferBuilder(9169620906008940582L));
        f.add(new BufferBuilder(2220623140383037870L));
        f.add(new BufferBuilder(-8255853930542279380L));
        f.add(new BufferBuilder(-9133093825005027372L));
        f.add(new BufferBuilder(-2619394491688261731L));
        f.add(new BufferBuilder(-5731627583554575565L));
        f.add(new BufferBuilder(-3104788460067762055L));
        f.add(new BufferBuilder(-8631057032720844289L));
        f.add(new BufferBuilder(-3385516179611325473L));
        f.add(new BufferBuilder(480030360162921353L));
        f.add(new BufferBuilder(6039286667926823083L));
        f.add(new BufferBuilder(8084756544564434374L));
        f.add(new BufferBuilder(7840685803402984610L));
        f.add(new BufferBuilder(6652567368545854410L));
        f.add(new BufferBuilder(3761748448922613992L));
        f.add(new BufferBuilder(-2875716036569566065L));
        f.add(new BufferBuilder(7514968061014522184L));
        f.add(new BufferBuilder(8046063563255234393L));
        f.add(new BufferBuilder(-7138091896967431311L));
        f.add(new BufferBuilder(7013345211789515738L));
        f.add(new BufferBuilder(-3419900200944686621L));
        f.add(new BufferBuilder(-1790361784662263329L));
        f.add(new BufferBuilder(-7520036821077760811L));
        f.add(new BufferBuilder(4571498940008874769L));
        f.add(new BufferBuilder(-6301920718176892225L));
        f.add(new BufferBuilder(6847852723986915512L));
        f.add(new BufferBuilder(-8871120406912112542L));
        f.add(new BufferBuilder(8704261413817878067L));
        f.add(new BufferBuilder(-3003163276626445084L));
        f.add(new BufferBuilder(-5001333279867624603L));
        f.add(new BufferBuilder(-1804155245918331457L));
        f.add(new BufferBuilder(7998271564227728631L));
        f.add(new BufferBuilder(2965203111727702834L));
        f.add(new BufferBuilder(-1429584554936332603L));
        f.add(new BufferBuilder(9120542136657028315L));
        f.add(new BufferBuilder(7314805189062818572L));
        f.add(new BufferBuilder(565362480503707533L));
        f.add(new BufferBuilder(2637788516199297132L));
        f.add(new BufferBuilder(3795287927194643303L));
        f.add(new BufferBuilder(-5081752671471368731L));
        f.add(new BufferBuilder(2100195983037871765L));
        f.add(new BufferBuilder(8914697494315747397L));
        f.add(new BufferBuilder(-8645312619684117312L));
        f.add(new BufferBuilder(-6921559395901636935L));
        f.add(new BufferBuilder(-7858487034989571839L));
        f.add(new BufferBuilder(-3473413541210276334L));
        f.add(new BufferBuilder(-8620318942608024785L));
        f.add(new BufferBuilder(-3009680880449566834L));
        f.add(new BufferBuilder(4827410162002474661L));
        f.add(new BufferBuilder(-6648596759026214636L));
        f.add(new BufferBuilder(2447686181178206431L));
        f.add(new BufferBuilder(3307052928172701441L));
        f.add(new BufferBuilder(-8449655312355517012L));
        f.add(new BufferBuilder(517118881723393964L));
        f.add(new BufferBuilder(1410799239327452363L));
        f.add(new BufferBuilder(-4919438492050825310L));
        f.add(new BufferBuilder(3840204540650184027L));
        f.add(new BufferBuilder(7209964992474337491L));
        f.add(new BufferBuilder(1036349358075585583L));
        f.add(new BufferBuilder(6790989864654878992L));
        f.add(new BufferBuilder(7013345212322192346L));
        f.add(new BufferBuilder(3895912108304790012L));
        f.add(new BufferBuilder(-4029791209564322295L));
        f.add(new BufferBuilder(5933335750904547587L));
        f.add(new BufferBuilder(-3123819758615211174L));
        f.add(new BufferBuilder(-349522063931870891L));
        f.add(new BufferBuilder(2478831087730658872L));
        f.add(new BufferBuilder(753531996277914732L));
        f.add(new BufferBuilder(5906584303072952396L));
        f.add(new BufferBuilder(4450735810476265692L));
        f.add(new BufferBuilder(3521849621924305754L));
        f.add(new BufferBuilder(-3345912550615431478L));
        f.add(new BufferBuilder(-5696675560956383113L));
        f.add(new BufferBuilder(-5803350423011154371L));
        f.add(new BufferBuilder(-1391315349120264828L));
        f.add(new BufferBuilder(6060990998616473848L));
        f.add(new BufferBuilder(-4608273948488772909L));
        f.add(new BufferBuilder(-7536452400372430142L));
        f.add(new BufferBuilder(-6613449593881108506L));
        f.add(new BufferBuilder(293304533072705787L));
        f.add(new BufferBuilder(2573834056112886171L));
        f.add(new BufferBuilder(-7490694996649590109L));
        f.add(new BufferBuilder(-7329261363439713554L));
        f.add(new BufferBuilder(107078086729902191L));
        f.add(new BufferBuilder(2335789328028052309L));
        f.add(new BufferBuilder(-282894071089477983L));
        f.add(new BufferBuilder(-7727019617399716639L));
        f.add(new BufferBuilder(-2099843059773517341L));
        f.add(new BufferBuilder(-7484670715823761952L));
        f.add(new BufferBuilder(362424616834316660L));
        f.add(new BufferBuilder(4559276986041958463L));
        f.add(new BufferBuilder(-3492797716540693541L));
        f.add(new BufferBuilder(3735841482398294446L));
        f.add(new BufferBuilder(2546247923713449332L));
        f.add(new BufferBuilder(7508822172419760337L));
        e = (int[])e.clone();
        f.add(new BufferBuilder(-4507573312931593981L));
        f.add(new BufferBuilder(4969688108754446360L));
        f.add(new BufferBuilder(-7327447264897149503L));
        f.add(new BufferBuilder(-8096040726734255061L));
        f.add(new BufferBuilder(3192865056809656223L));
        f.add(new BufferBuilder(724386031136409549L));
        f.add(new BufferBuilder(-4328348439121582172L));
        f.add(new BufferBuilder(4044404171858881298L));
        f.add(new BufferBuilder(-4562651201026727673L));
        f.add(new BufferBuilder(-412568846541351614L));
        f.add(new BufferBuilder(-8884997054237220797L));
        f.add(new BufferBuilder(-5290813164180535145L));
        f.add(new BufferBuilder(969152962983353066L));
        f.add(new BufferBuilder(-1479276402428381667L));
        f.add(new BufferBuilder(3095460421324039010L));
        f.add(new BufferBuilder(-1741233852586857765L));
        f.add(new BufferBuilder(-6449438365641848396L));
        f.add(new BufferBuilder(-6911484174626547151L));
        f.add(new BufferBuilder(-7538052100831702449L));
        f.add(new BufferBuilder(-3736586654999969262L));
        f.add(new BufferBuilder(-228127507834083523L));
        f.add(new BufferBuilder(2964522404659409421L));
        f.add(new BufferBuilder(-5401466814156212649L));
        f.add(new BufferBuilder(4531987010705171481L));
        f.add(new BufferBuilder(-8681760582742267861L));
        f.add(new BufferBuilder(-550212750669905301L));
        f.add(new BufferBuilder(8485470948937086188L));
        f.add(new BufferBuilder(392497127337159576L));
        f.add(new BufferBuilder(1220601375613365252L));
        f.add(new BufferBuilder(6813717532953358307L));
        f.add(new BufferBuilder(5904279722566813125L));
        f.add(new BufferBuilder(-3251071057764477494L));
        f.add(new BufferBuilder(6764064667525819760L));
        f.add(new BufferBuilder(-6550736302283523773L));
        f.add(new BufferBuilder(1418700438429957847L));
        f.add(new BufferBuilder(-1963393459583863107L));
        f.add(new BufferBuilder(-3819149580893653667L));
        f.add(new BufferBuilder(-566714325194679909L));
        f.add(new BufferBuilder(7163264590829405160L));
        f.add(new BufferBuilder(7189741961086549003L));
        f.add(new BufferBuilder(-726759380906604024L));
        f.add(new BufferBuilder(8651176871732178519L));
        f.add(new BufferBuilder(5591912164137918826L));
        f.add(new BufferBuilder(8962426294288102163L));
        f.add(new BufferBuilder(533259435991812287L));
        f.add(new BufferBuilder(-4889101532730600864L));
        f.add(new BufferBuilder(4654419175048418499L));
        f.add(new BufferBuilder(-7754839692875981863L));
        f.add(new BufferBuilder(-6490793937330822568L));
        f.add(new BufferBuilder(1779556679324156225L));
        f.add(new BufferBuilder(-886376610571498787L));
        f.add(new BufferBuilder(-239099577974482963L));
        f.add(new BufferBuilder(-2189039278564277722L));
        f.add(new BufferBuilder(2682756190070748239L));
        f.add(new BufferBuilder(-7528036009271097703L));
        f.add(new BufferBuilder(-2844856053560503144L));
        f.add(new BufferBuilder(4164976268298949625L));
        f.add(new BufferBuilder(4224194717724061635L));
        f.add(new BufferBuilder(-8903858860338734213L));
        f.add(new BufferBuilder(-9182452504025957095L));
        f.add(new BufferBuilder(5722204980547692226L));
        f.add(new BufferBuilder(-8415979233145353894L));
        f.add(new BufferBuilder(5632274728948935092L));
        f.add(new BufferBuilder(1701073020487537599L));
        f.add(new BufferBuilder(-3446584507993455726L));
        f.add(new BufferBuilder(-8759840797971617519L));
        f.add(new BufferBuilder(6274238678108271039L));
        f.add(new BufferBuilder(3019857854548910541L));
        f.add(new BufferBuilder(-2579755742048294136L));
        f.add(new BufferBuilder(6178400002013856079L));
        f.add(new BufferBuilder(8136924942375159791L));
        f.add(new BufferBuilder(7908235846183619198L));
        f.add(new BufferBuilder(-1421870622184207720L));
        f.add(new BufferBuilder(1129436866060775448L));
        f.add(new BufferBuilder(-7174273600719475041L));
        f.add(new BufferBuilder(6365430048010859300L));
        f.add(new BufferBuilder(4335967243742614086L));
        f.add(new BufferBuilder(7178116539055578143L));
        f.add(new BufferBuilder(-3876667482938256636L));
        f.add(new BufferBuilder(1762237054424866060L));
        f.add(new BufferBuilder(5168951482988790105L));
        f.add(new BufferBuilder(3534917506846842045L));
        f.add(new BufferBuilder(-5210238126239421448L));
        f.add(new BufferBuilder(-376715362765793929L));
        f.add(new BufferBuilder(-8205928572130504419L));
        f.add(new BufferBuilder(-1701579417749604138L));
        f.add(new BufferBuilder(-490584826277096727L));
        f.add(new BufferBuilder(-5483618421257560661L));
        f.add(new BufferBuilder(-155714947325897395L));
        f.add(new BufferBuilder(447825010347655123L));
        f.add(new BufferBuilder(7785860798578226872L));
        f.add(new BufferBuilder(-6858601882960796241L));
        f.add(new BufferBuilder(-8434789379110081313L));
        f.add(new BufferBuilder(3250345891831838315L));
        f.add(new BufferBuilder(-2243473517525509944L));
        f.add(new BufferBuilder(238483228380432187L));
        f.add(new BufferBuilder(7598384277512142590L));
        f.add(new BufferBuilder(7972894426536829483L));
        f.add(new BufferBuilder(-8577407054518260091L));
        f.add(new BufferBuilder(-8509938871972282608L));
        f.add(new BufferBuilder(8208526115894662137L));
        f.add(new BufferBuilder(-9080651589604252519L));
        f.add(new BufferBuilder(5762029147522677143L));
        f.add(new BufferBuilder(-7206761513318781507L));
        f.add(new BufferBuilder(149021489830997631L));
        f.add(new BufferBuilder(4221060753902180172L));
        f.add(new BufferBuilder(346917538632320944L));
        f.add(new BufferBuilder(2900533090829468808L));
        f.add(new BufferBuilder(1351562871504462918L));
        f.add(new BufferBuilder(3003253653435103343L));
        f.add(new BufferBuilder(-2802318660257690500L));
        f.add(new BufferBuilder(-8140978134261112558L));
        f.add(new BufferBuilder(-9000428116156804155L));
        f.add(new BufferBuilder(-7766926974756299131L));
        f.add(new BufferBuilder(-3666394438566047388L));
        f.add(new BufferBuilder(-1925754878210956931L));
        f.add(new BufferBuilder(-4731318957191578091L));
        f.add(new BufferBuilder(-5640193736764572020L));
        f.add(new BufferBuilder(5012151065066929557L));
        f.add(new BufferBuilder(-6772790365730791238L));
        f.add(new BufferBuilder(-1877919425177077902L));
        f.add(new BufferBuilder(6838707680855638647L));
        f.add(new BufferBuilder(3707218867313986359L));
        f.add(new BufferBuilder(5334697164688405229L));
        f.add(new BufferBuilder(2209768819642973280L));
        f.add(new BufferBuilder(1747691013822565278L));
        f.add(new BufferBuilder(3707218867276237623L));
        f.add(new BufferBuilder(14853951359637924L));
        f.add(new BufferBuilder(5500920932239751725L));
        f.add(new BufferBuilder(3584996334702698606L));
        f.add(new BufferBuilder(1293401758081391198L));
        f.add(new BufferBuilder(-2618821376564613293L));
        f.add(new BufferBuilder(4946019319576176285L));
        f.add(new BufferBuilder(-2565718076225246616L));
        f.add(new BufferBuilder(-5823445943452878704L));
        f.add(new BufferBuilder(-1843343212271465827L));
        f.add(new BufferBuilder(-5571243344248681403L));
        f.add(new BufferBuilder(-3426042744963093114L));
        f.add(new BufferBuilder(-3227678706738354621L));
        f.add(new BufferBuilder(4127432454445875651L));
        f.add(new BufferBuilder(-7257574875923685255L));
        f.add(new BufferBuilder(9184072969325197538L));
        f.add(new BufferBuilder(-7487014796638551434L));
        f.add(new BufferBuilder(2852604758792850905L));
        f.add(new BufferBuilder(1628345351610981302L));
        f.add(new BufferBuilder(-7858840903612052111L));
        f.add(new BufferBuilder(3152497757318344037L));
        f.add(new BufferBuilder(5640637659136476993L));
        f.add(new BufferBuilder(8024918156175388807L));
        f.add(new BufferBuilder(902279764272780834L));
        f.add(new BufferBuilder(4162368278033845103L));
        e = (int[])e.clone();
        f.add(new BufferBuilder(9035885924355795442L));
        f.add(new BufferBuilder(-2383892320639031841L));
        f.add(new BufferBuilder(-7968927618843480297L));
        f.add(new BufferBuilder(1819601608400353300L));
        f.add(new BufferBuilder(7109669670800431397L));
        f.add(new BufferBuilder(5475312939540424170L));
        f.add(new BufferBuilder(-4977187420753839346L));
        f.add(new BufferBuilder(-807121908856456310L));
        f.add(new BufferBuilder(1100529213284449858L));
        f.add(new BufferBuilder(8619457328723057101L));
        f.add(new BufferBuilder(854120386567158670L));
        f.add(new BufferBuilder(8802137452414066739L));
        f.add(new BufferBuilder(-5481366347102839383L));
        f.add(new BufferBuilder(7847371306412183404L));
        f.add(new BufferBuilder(741913688461880527L));
        f.add(new BufferBuilder(-7757153689159361350L));
        f.add(new BufferBuilder(-6892605942376367479L));
        f.add(new BufferBuilder(7995361466660913872L));
        f.add(new BufferBuilder(6649665343611064195L));
        f.add(new BufferBuilder(-7876275862232681429L));
        f.add(new BufferBuilder(-2561124447871992606L));
        f.add(new BufferBuilder(5986368074398878947L));
        f.add(new BufferBuilder(8846186829863880020L));
        f.add(new BufferBuilder(7669519456527987048L));
        f.add(new BufferBuilder(-5798699426951820081L));
        f.add(new BufferBuilder(-547765375561617929L));
        f.add(new BufferBuilder(-3734352445229039086L));
        f.add(new BufferBuilder(4148504748129536577L));
        f.add(new BufferBuilder(-4953796719980899236L));
        f.add(new BufferBuilder(-7034128960803392775L));
        f.add(new BufferBuilder(1863630688749393656L));
        f.add(new BufferBuilder(-7784651855807078391L));
        f.add(new BufferBuilder(-7485561786949184877L));
        f.add(new BufferBuilder(7912092214529251083L));
        f.add(new BufferBuilder(-9175052361318473424L));
        f.add(new BufferBuilder(5033335044920531374L));
        f.add(new BufferBuilder(145428828407112855L));
        f.add(new BufferBuilder(706459091942160412L));
        f.add(new BufferBuilder(6101851233520824852L));
        f.add(new BufferBuilder(7818286517929283394L));
        f.add(new BufferBuilder(-2031543907312097190L));
        f.add(new BufferBuilder(-8519949837384812223L));
        f.add(new BufferBuilder(-6741447927674274290L));
        f.add(new BufferBuilder(-1008823245031301532L));
        f.add(new BufferBuilder(147964057019424140L));
        f.add(new BufferBuilder(-6662652089804892848L));
        f.add(new BufferBuilder(7885223008856918985L));
        f.add(new BufferBuilder(-7694260132411185701L));
        f.add(new BufferBuilder(-1829830024333489653L));
        f.add(new BufferBuilder(6802132255692503247L));
        f.add(new BufferBuilder(-6403308769558910047L));
        f.add(new BufferBuilder(-8228962538177585033L));
        f.add(new BufferBuilder(8275181439206457694L));
        f.add(new BufferBuilder(4974389503511956982L));
        f.add(new BufferBuilder(9166104389268944944L));
        f.add(new BufferBuilder(-8230377356994822848L));
        f.add(new BufferBuilder(544128274098877330L));
        f.add(new BufferBuilder(4925135953141930003L));
        f.add(new BufferBuilder(-1132776994769930150L));
        f.add(new BufferBuilder(3560607613155094804L));
        f.add(new BufferBuilder(6259024284252470999L));
        f.add(new BufferBuilder(2310030550478356473L));
        f.add(new BufferBuilder(5769554425761096831L));
        f.add(new BufferBuilder(3840182987297821556L));
        f.add(new BufferBuilder(6052274730067885821L));
        f.add(new BufferBuilder(4360619511364215196L));
        f.add(new BufferBuilder(552732582140815384L));
        f.add(new BufferBuilder(7469209389182829210L));
        f.add(new BufferBuilder(3223174063826680129L));
        f.add(new BufferBuilder(-9058735539716290016L));
        f.add(new BufferBuilder(4432820030474291616L));
        f.add(new BufferBuilder(5295910778554173854L));
        f.add(new BufferBuilder(-2029076850390525924L));
        f.add(new BufferBuilder(-1614626581330337385L));
        f.add(new BufferBuilder(8396678466604906004L));
        f.add(new BufferBuilder(510785927279739985L));
        f.add(new BufferBuilder(1824007638767086727L));
        f.add(new BufferBuilder(1612105165281563045L));
        f.add(new BufferBuilder(7490253072091146579L));
        f.add(new BufferBuilder(-3963602946598801208L));
        f.add(new BufferBuilder(-3236490049851622210L));
        f.add(new BufferBuilder(-3457667714076108306L));
        f.add(new BufferBuilder(7154472939818439907L));
        f.add(new BufferBuilder(-3285046754612840325L));
        f.add(new BufferBuilder(-1958129843801226489L));
        f.add(new BufferBuilder(-2440894942280872083L));
        f.add(new BufferBuilder(6717516198193632873L));
        f.add(new BufferBuilder(-3671788272970387124L));
        f.add(new BufferBuilder(-4724946842410074629L));
        f.add(new BufferBuilder(-8126390047296113604L));
        f.add(new BufferBuilder(-8642214516611679845L));
        f.add(new BufferBuilder(-687342861358744318L));
        f.add(new BufferBuilder(2112303941230088962L));
        f.add(new BufferBuilder(-3955396209230230514L));
        f.add(new BufferBuilder(51672895699272424L));
        f.add(new BufferBuilder(8198065535924326222L));
        f.add(new BufferBuilder(2268214940211254591L));
        f.add(new BufferBuilder(-3302476877391067853L));
        f.add(new BufferBuilder(-390890878898933521L));
        f.add(new BufferBuilder(-5823247906111658340L));
        f.add(new BufferBuilder(7677069175059439791L));
        f.add(new BufferBuilder(-743379054980606337L));
        f.add(new BufferBuilder(2714782591602420950L));
        f.add(new BufferBuilder(3162505932962500600L));
        f.add(new BufferBuilder(67020481479601040L));
        f.add(new BufferBuilder(1508705572514919840L));
        f.add(new BufferBuilder(6809845283582499616L));
        f.add(new BufferBuilder(-4673100068722590363L));
        f.add(new BufferBuilder(-4845198820828080079L));
        f.add(new BufferBuilder(6698231729282425649L));
        f.add(new BufferBuilder(2416159116879224374L));
        f.add(new BufferBuilder(1197337001695656654L));
        f.add(new BufferBuilder(-6311080791365363670L));
        f.add(new BufferBuilder(-400144637383126864L));
        f.add(new BufferBuilder(-9210186395272016963L));
        f.add(new BufferBuilder(8344751206387992903L));
        f.add(new BufferBuilder(3902936370882686556L));
        f.add(new BufferBuilder(5382945887048254474L));
        f.add(new BufferBuilder(4999909663385772064L));
        f.add(new BufferBuilder(-511057243617691169L));
        f.add(new BufferBuilder(2749242909607077890L));
        f.add(new BufferBuilder(-1415087044807206189L));
        f.add(new BufferBuilder(2135504652165291765L));
        f.add(new BufferBuilder(697363811122641924L));
        f.add(new BufferBuilder(2257982372647910656L));
        f.add(new BufferBuilder(-3709075209219025935L));
        f.add(new BufferBuilder(5322646016101479201L));
        f.add(new BufferBuilder(274127640865488684L));
        f.add(new BufferBuilder(-4077796808440698927L));
        f.add(new BufferBuilder(-5081946843548307785L));
        f.add(new BufferBuilder(-4325439648359611519L));
        f.add(new BufferBuilder(-4526624045412905775L));
        f.add(new BufferBuilder(5578796072761222230L));
        f.add(new BufferBuilder(-2051157482484143167L));
        f.add(new BufferBuilder(-2609734439402827141L));
        f.add(new BufferBuilder(2837216223228577452L));
        f.add(new BufferBuilder(5180226259353583937L));
        f.add(new BufferBuilder(-8992894653239729604L));
        f.add(new BufferBuilder(-1455606636788764008L));
        f.add(new BufferBuilder(-1284143922600941688L));
        f.add(new BufferBuilder(868978258659498271L));
        f.add(new BufferBuilder(-6215175181676027251L));
        f.add(new BufferBuilder(-2509648422241305684L));
        f.add(new BufferBuilder(-1821244077372800426L));
        f.add(new BufferBuilder(3718575180984075362L));
        f.add(new BufferBuilder(6845583331949437112L));
        f.add(new BufferBuilder(-5042750171742278578L));
        f.add(new BufferBuilder(-8585425014297291472L));
        f.add(new BufferBuilder(-8655874187941514161L));
        f.add(new BufferBuilder(6346110152718083926L));
        f.add(new BufferBuilder(5153715718038374590L));
        f.add(new BufferBuilder(4774123094208051593L));
        f.add(new BufferBuilder(2834541042014381412L));
        f.add(new BufferBuilder(-3582181015230097655L));
        f.add(new BufferBuilder(-1019396610755716008L));
        f.add(new BufferBuilder(-5846814379084437544L));
        f.add(new BufferBuilder(-1010277375771873799L));
        f.add(new BufferBuilder(2269795881230703218L));
        f.add(new BufferBuilder(-7062056896997212705L));
        f.add(new BufferBuilder(-1679697373161939447L));
        f.add(new BufferBuilder(-2245725042427733816L));
        f.add(new BufferBuilder(-5796899720068075298L));
        f.add(new BufferBuilder(-760475274441803744L));
        f.add(new BufferBuilder(1009521602034960175L));
        f.add(new BufferBuilder(-6919916631721824128L));
        f.add(new BufferBuilder(8714001708050828496L));
        f.add(new BufferBuilder(-6234963159224780552L));
        f.add(new BufferBuilder(-8708291627771801059L));
        f.add(new BufferBuilder(-2387869161932670525L));
        f.add(new BufferBuilder(-6649885403994102489L));
        f.add(new BufferBuilder(-6003267671257199589L));
        e = (int[])e.clone();
        f.add(new BufferBuilder(-7440526568183905889L));
        f.add(new BufferBuilder(6156758668110287452L));
        f.add(new BufferBuilder(-2180869133918455012L));
        f.add(new BufferBuilder(-4897876226090702751L));
        f.add(new BufferBuilder(-6203943409593918500L));
        f.add(new BufferBuilder(-7956029911936723963L));
        f.add(new BufferBuilder(-4894248378385632503L));
        f.add(new BufferBuilder(-5035664644857577919L));
        f.add(new BufferBuilder(-316275458786100225L));
        f.add(new BufferBuilder(7247428582350608130L));
        f.add(new BufferBuilder(-6876090141930314800L));
        f.add(new BufferBuilder(1382098645648098426L));
        f.add(new BufferBuilder(-5517584460492084528L));
        f.add(new BufferBuilder(4209862795273782085L));
        f.add(new BufferBuilder(1148486162738993508L));
        f.add(new BufferBuilder(2537911749232126327L));
        f.add(new BufferBuilder(679489308090067613L));
        f.add(new BufferBuilder(5447176630578314611L));
        f.add(new BufferBuilder(3181049237446743148L));
        f.add(new BufferBuilder(-8688476427300017079L));
        f.add(new BufferBuilder(-5298271687525067686L));
        f.add(new BufferBuilder(4270056678766172429L));
        f.add(new BufferBuilder(2945008548930711460L));
        f.add(new BufferBuilder(7911230163538260109L));
        f.add(new BufferBuilder(-2599258955774664346L));
        f.add(new BufferBuilder(-5394707107938652861L));
        f.add(new BufferBuilder(-2428099541409323017L));
        f.add(new BufferBuilder(-6720260663785708036L));
        f.add(new BufferBuilder(6937663518819585567L));
        f.add(new BufferBuilder(3898066813745748459L));
        f.add(new BufferBuilder(-3837544784039908015L));
        f.add(new BufferBuilder(7831599176526910745L));
        f.add(new BufferBuilder(5897909866754633760L));
        f.add(new BufferBuilder(2812739633753263226L));
        f.add(new BufferBuilder(-4259278317348770596L));
        f.add(new BufferBuilder(4316306091048109095L));
        f.add(new BufferBuilder(-6542169212731619922L));
        f.add(new BufferBuilder(3870792203987504056L));
        f.add(new BufferBuilder(-5644960290693587352L));
        f.add(new BufferBuilder(1495819553149533510L));
        f.add(new BufferBuilder(-1676049854900666518L));
        f.add(new BufferBuilder(5026770972139786519L));
        f.add(new BufferBuilder(-4432860700731047657L));
        f.add(new BufferBuilder(-404452542131410348L));
        f.add(new BufferBuilder(6167850145663711516L));
        f.add(new BufferBuilder(-7161259033177445761L));
        f.add(new BufferBuilder(-7935331784882986385L));
        f.add(new BufferBuilder(6650308208397998438L));
        f.add(new BufferBuilder(-3880908349645617801L));
        f.add(new BufferBuilder(-8050948936463234364L));
        f.add(new BufferBuilder(576550016234137024L));
        f.add(new BufferBuilder(-2142488216229812598L));
        f.add(new BufferBuilder(8803635047380366296L));
        f.add(new BufferBuilder(-7442963949812401493L));
        f.add(new BufferBuilder(-6221256135674372324L));
        f.add(new BufferBuilder(1987074826707156729L));
        f.add(new BufferBuilder(1262692516382253483L));
        f.add(new BufferBuilder(1256630894800980638L));
        f.add(new BufferBuilder(-5244262098849063481L));
        f.add(new BufferBuilder(1889117797242472467L));
        f.add(new BufferBuilder(3828015087508153307L));
        f.add(new BufferBuilder(7567945637854714064L));
        f.add(new BufferBuilder(-3233151798691820529L));
        f.add(new BufferBuilder(3596735949522644038L));
        f.add(new BufferBuilder(-1966143512881344948L));
        f.add(new BufferBuilder(5072240701308363185L));
        f.add(new BufferBuilder(1011453269306704631L));
        f.add(new BufferBuilder(-9106465775666771632L));
        f.add(new BufferBuilder(3777172214928757932L));
        f.add(new BufferBuilder(2975749223989780795L));
        f.add(new BufferBuilder(3865455161114591693L));
        f.add(new BufferBuilder(-2476618753328617637L));
        f.add(new BufferBuilder(-5506836053213281519L));
        f.add(new BufferBuilder(4957881590102746311L));
        f.add(new BufferBuilder(-5387352714388643840L));
        f.add(new BufferBuilder(-4880542776371311388L));
        f.add(new BufferBuilder(3556698929397729278L));
        f.add(new BufferBuilder(1379588467864332037L));
        f.add(new BufferBuilder(-8286519606776733339L));
        f.add(new BufferBuilder(-7597059744730779482L));
        f.add(new BufferBuilder(-4171703978307540362L));
        f.add(new BufferBuilder(-8503296912472232814L));
        f.add(new BufferBuilder(-1836000162419816933L));
        f.add(new BufferBuilder(-8459800239775838177L));
        f.add(new BufferBuilder(68237791024391869L));
        f.add(new BufferBuilder(-1594673341560067631L));
        f.add(new BufferBuilder(-1586157104595294501L));
        f.add(new BufferBuilder(996447186451924476L));
        f.add(new BufferBuilder(-4044667892809757484L));
        e = (int[])e.clone();
        f.add(new BufferBuilder(7194611807492143477L));
        f.add(new BufferBuilder(-4352393048069519588L));
        f.add(new BufferBuilder(3906805856339614210L));
        f.add(new BufferBuilder(5042661540534080612L));
        f.add(new BufferBuilder(260368455315165630L));
        f.add(new BufferBuilder(7400637213508074159L));
        f.add(new BufferBuilder(8281333240160128986L));
        f.add(new BufferBuilder(2480365330496235741L));
        f.add(new BufferBuilder(-511056966059623971L));
        f.add(new BufferBuilder(-4313960534301964935L));
        f.add(new BufferBuilder(5918507970513861399L));
        f.add(new BufferBuilder(-7700478525502827930L));
        f.add(new BufferBuilder(-4905263155643456743L));
        f.add(new BufferBuilder(-4634131963090861169L));
        f.add(new BufferBuilder(7487598097565324318L));
        f.add(new BufferBuilder(-160771419540465096L));
        f.add(new BufferBuilder(-2588026457577557069L));
        f.add(new BufferBuilder(5152696234099833531L));
        f.add(new BufferBuilder(-3215688213030996125L));
        f.add(new BufferBuilder(7833138498952916627L));
        f.add(new BufferBuilder(-7211227333287858577L));
        f.add(new BufferBuilder(-8437616579748087040L));
        f.add(new BufferBuilder(-6366014460863168035L));
        f.add(new BufferBuilder(-7414008550681395440L));
        f.add(new BufferBuilder(-3105147124278922083L));
        f.add(new BufferBuilder(-8034488008081184114L));
        f.add(new BufferBuilder(6414056424210632766L));
        f.add(new BufferBuilder(236241547618744006L));
        f.add(new BufferBuilder(-6324463900945014517L));
        f.add(new BufferBuilder(7393944907926405664L));
        f.add(new BufferBuilder(1096199277483231935L));
        f.add(new BufferBuilder(-5250178245348591369L));
        f.add(new BufferBuilder(-6032329200928558043L));
        f.add(new BufferBuilder(3798297198429766254L));
        f.add(new BufferBuilder(2939788152909114367L));
        f.add(new BufferBuilder(3335773287657439048L));
        f.add(new BufferBuilder(-7268416394473303142L));
        f.add(new BufferBuilder(7396375367881182017L));
        f.add(new BufferBuilder(-6650287388764453287L));
        f.add(new BufferBuilder(-698928160411988668L));
        f.add(new BufferBuilder(-2580182530330002117L));
        f.add(new BufferBuilder(2574829008595077523L));
        f.add(new BufferBuilder(2277037531626032968L));
        f.add(new BufferBuilder(97174581517551533L));
        f.add(new BufferBuilder(-522268387495371998L));
        f.add(new BufferBuilder(8132937484404317113L));
        f.add(new BufferBuilder(-2432486304166371790L));
        f.add(new BufferBuilder(4077027749470964641L));
        f.add(new BufferBuilder(-8546536924051911923L));
        f.add(new BufferBuilder(3548708528391679548L));
        f.add(new BufferBuilder(8199031872002032713L));
        f.add(new BufferBuilder(-9035984241698882776L));
        f.add(new BufferBuilder(8008878767808386559L));
        f.add(new BufferBuilder(2133604434935026528L));
        f.add(new BufferBuilder(6020794306108167782L));
        f.add(new BufferBuilder(-1369418506439763782L));
        f.add(new BufferBuilder(-3795071103210604255L));
        f.add(new BufferBuilder(-7489123775042719052L));
        f.add(new BufferBuilder(-2913421222176884288L));
        f.add(new BufferBuilder(1186517764039389177L));
        f.add(new BufferBuilder(-5143342074004177217L));
        f.add(new BufferBuilder(-665959220152718065L));
        f.add(new BufferBuilder(6533592956801420154L));
        f.add(new BufferBuilder(1671200554272959843L));
        f.add(new BufferBuilder(-8632649837157512931L));
        f.add(new BufferBuilder(-7721067306330703562L));
        f.add(new BufferBuilder(-4444849123756221879L));
        f.add(new BufferBuilder(-5482134716169106735L));
        f.add(new BufferBuilder(-4941681585582219511L));
        f.add(new BufferBuilder(-3121554493212692642L));
        f.add(new BufferBuilder(-7633694913667279977L));
        f.add(new BufferBuilder(2978566078011787624L));
        f.add(new BufferBuilder(-4156110841717767763L));
        f.add(new BufferBuilder(-4986900496118599635L));
        f.add(new BufferBuilder(2333555117716056917L));
        f.add(new BufferBuilder(-8198501786811748618L));
        f.add(new BufferBuilder(-7517525642441491462L));
        f.add(new BufferBuilder(-7408585420761217213L));
        f.add(new BufferBuilder(-6665066358654180103L));
        f.add(new BufferBuilder(6330722968131740176L));
        f.add(new BufferBuilder(-1059806530309099883L));
        f.add(new BufferBuilder(-3750474676767440882L));
        f.add(new BufferBuilder(3365372585042491392L));
        f.add(new BufferBuilder(-5660756737929676716L));
        f.add(new BufferBuilder(-8549262200946064635L));
        f.add(new BufferBuilder(-7269661545783398932L));
        f.add(new BufferBuilder(-733020014666880481L));
        f.add(new BufferBuilder(491166540543540322L));
        f.add(new BufferBuilder(-7979609498273952106L));
        f.add(new BufferBuilder(-4058214869452769295L));
        f.add(new BufferBuilder(6041096742414723469L));
        f.add(new BufferBuilder(8885428953281285970L));
        f.add(new BufferBuilder(-8330022530239753654L));
        f.add(new BufferBuilder(1158348072175901835L));
        f.add(new BufferBuilder(8908141079845941244L));
        f.add(new BufferBuilder(3376831176586142397L));
        f.add(new BufferBuilder(5810831267685733851L));
        f.add(new BufferBuilder(-6838693081607167987L));
        f.add(new BufferBuilder(-9016515759500685353L));
        f.add(new BufferBuilder(-6414939439895580597L));
        e = (int[])e.clone();
        f.add(new BufferBuilder(7712788214418993318L));
        f.add(new BufferBuilder(7695466496764279985L));
        f.add(new BufferBuilder(-7714353975616493005L));
        f.add(new BufferBuilder(-4489093845492857878L));
        f.add(new BufferBuilder(7371936273344859364L));
        f.add(new BufferBuilder(-1275720082921138482L));
        f.add(new BufferBuilder(7205850615503784474L));
        f.add(new BufferBuilder(-8695489996825025194L));
        f.add(new BufferBuilder(-2699136083910985977L));
        f.add(new BufferBuilder(7517470908442235209L));
        f.add(new BufferBuilder(528299576118159307L));
        f.add(new BufferBuilder(3904920259224149390L));
        f.add(new BufferBuilder(1596277818611700562L));
        f.add(new BufferBuilder(993337473434171345L));
        f.add(new BufferBuilder(-347822687323467049L));
        f.add(new BufferBuilder(8495130231125778379L));
        f.add(new BufferBuilder(-1624794849513101516L));
        f.add(new BufferBuilder(-1406911749201454344L));
        f.add(new BufferBuilder(6369741211469356398L));
        f.add(new BufferBuilder(8797472250477284093L));
        f.add(new BufferBuilder(-3946286476244711619L));
        f.add(new BufferBuilder(2296979928492808976L));
        f.add(new BufferBuilder(-1882153090704318253L));
        f.add(new BufferBuilder(4581136110266874596L));
        f.add(new BufferBuilder(8964330307289485741L));
        f.add(new BufferBuilder(-3013368468762667339L));
        f.add(new BufferBuilder(7917634157973433430L));
        f.add(new BufferBuilder(-339396814779504544L));
        f.add(new BufferBuilder(-6289771260813322396L));
        f.add(new BufferBuilder(5140750397409107881L));
        f.add(new BufferBuilder(-3569350557602230405L));
        f.add(new BufferBuilder(3589057271848037803L));
        f.add(new BufferBuilder(2480195133585928898L));
        f.add(new BufferBuilder(-8563404018675753072L));
        f.add(new BufferBuilder(1501621240813736459L));
        f.add(new BufferBuilder(8322358959384581471L));
        f.add(new BufferBuilder(-653027339065365112L));
        f.add(new BufferBuilder(-203049341709825718L));
        f.add(new BufferBuilder(1640587613390039050L));
        f.add(new BufferBuilder(-4530501174930534868L));
        f.add(new BufferBuilder(3186914366195451143L));
        f.add(new BufferBuilder(-495667689800903707L));
        f.add(new BufferBuilder(-3412607594115736718L));
        f.add(new BufferBuilder(3941910061354199523L));
        f.add(new BufferBuilder(7266353397777843178L));
        f.add(new BufferBuilder(5299115493849569322L));
        f.add(new BufferBuilder(-5284298558306306876L));
        f.add(new BufferBuilder(-3659932271796936772L));
        f.add(new BufferBuilder(6363527853669208252L));
        f.add(new BufferBuilder(6223300590167008608L));
        f.add(new BufferBuilder(-1864308400053814804L));
        f.add(new BufferBuilder(5020203188742886829L));
        f.add(new BufferBuilder(-7656958068870220240L));
        f.add(new BufferBuilder(-6158169085610555379L));
        f.add(new BufferBuilder(3692083573231337056L));
        f.add(new BufferBuilder(-3393312413326356140L));
        f.add(new BufferBuilder(-3278959195806944513L));
        f.add(new BufferBuilder(-6741460312569373039L));
        f.add(new BufferBuilder(347202854676663823L));
        f.add(new BufferBuilder(9147550993149053471L));
        f.add(new BufferBuilder(-2956119407498607380L));
        f.add(new BufferBuilder(820711975769859052L));
        f.add(new BufferBuilder(-4169280023403398061L));
        f.add(new BufferBuilder(-1664372214857417168L));
        f.add(new BufferBuilder(-590704556100656662L));
        f.add(new BufferBuilder(-4986950013872162698L));
        f.add(new BufferBuilder(2789778908193713454L));
        f.add(new BufferBuilder(6793043414595196157L));
        f.add(new BufferBuilder(-1037076853726009203L));
        f.add(new BufferBuilder(5852035236592156208L));
        f.add(new BufferBuilder(-6200970324026265889L));
        f.add(new BufferBuilder(9207262951156712642L));
        f.add(new BufferBuilder(-4632257845710430727L));
        f.add(new BufferBuilder(-1949141042671654070L));
        f.add(new BufferBuilder(1712458643542428529L));
        f.add(new BufferBuilder(-469217054423708197L));
        f.add(new BufferBuilder(-8339253408185516058L));
        f.add(new BufferBuilder(-9097062387585685666L));
        f.add(new BufferBuilder(-5898815773687287774L));
        f.add(new BufferBuilder(3018262258718932077L));
        f.add(new BufferBuilder(-3266638052767450498L));
        f.add(new BufferBuilder(9197071657903865415L));
        f.add(new BufferBuilder(-8626593925640026839L));
        f.add(new BufferBuilder(3636384374962437865L));
        f.add(new BufferBuilder(3598533075560352841L));
        f.add(new BufferBuilder(598464885550297277L));
        f.add(new BufferBuilder(-9021356661347896217L));
        f.add(new BufferBuilder(2696907247995016989L));
        f.add(new BufferBuilder(-6682714967232296324L));
        f.add(new BufferBuilder(9096347890628772427L));
        f.add(new BufferBuilder(-1090276406924284300L));
        f.add(new BufferBuilder(2738830194971780341L));
        f.add(new BufferBuilder(9173474092542983781L));
        f.add(new BufferBuilder(3980057531705299575L));
        f.add(new BufferBuilder(9076395743205490895L));
        f.add(new BufferBuilder(6665385205431997546L));
        f.add(new BufferBuilder(5978409233283138729L));
        f.add(new BufferBuilder(-6572364943168987259L));
        f.add(new BufferBuilder(-2700636807329387364L));
        f.add(new BufferBuilder(314376251440174350L));
        f.add(new BufferBuilder(2178485714412297087L));
        f.add(new BufferBuilder(-4678629540761556242L));
        f.add(new BufferBuilder(2813768477697956669L));
        f.add(new BufferBuilder(6473100734578730815L));
        f.add(new BufferBuilder(5132789364398787043L));
        f.add(new BufferBuilder(7568509416046274923L));
        f.add(new BufferBuilder(2987557685089745228L));
        f.add(new BufferBuilder(-7898730875441670778L));
        f.add(new BufferBuilder(-6327015860235129863L));
        f.add(new BufferBuilder(-3061600056543297309L));
        f.add(new BufferBuilder(-7885594535860153749L));
        f.add(new BufferBuilder(-5088726872925575201L));
        f.add(new BufferBuilder(-279557395697052887L));
        f.add(new BufferBuilder(-5059569935258661798L));
        f.add(new BufferBuilder(5144175481554119360L));
        f.add(new BufferBuilder(5145137001628849827L));
        f.add(new BufferBuilder(4459048807940030528L));
        f.add(new BufferBuilder(5572761078225506389L));
        f.add(new BufferBuilder(6946343296763491301L));
        f.add(new BufferBuilder(-8247950924415051668L));
        f.add(new BufferBuilder(-6892178977169308984L));
        f.add(new BufferBuilder(2860908877147668580L));
        f.add(new BufferBuilder(8389875924984551131L));
        f.add(new BufferBuilder(-7837477156994051789L));
        f.add(new BufferBuilder(5979250714775373091L));
        f.add(new BufferBuilder(-8575118083221853905L));
        f.add(new BufferBuilder(5065170967328814853L));
        f.add(new BufferBuilder(6034304712688596381L));
        f.add(new BufferBuilder(-8262456589652832291L));
        f.add(new BufferBuilder(4057760127383493089L));
        f.add(new BufferBuilder(-6536483149914686195L));
        f.add(new BufferBuilder(-7057226478305285580L));
        f.add(new BufferBuilder(-2276648798847236774L));
        f.add(new BufferBuilder(-7088309748852975981L));
        f.add(new BufferBuilder(6764246248527996567L));
        f.add(new BufferBuilder(-4767895482633794629L));
        f.add(new BufferBuilder(7639477256377047313L));
        f.add(new BufferBuilder(3254641459175126542L));
        f.add(new BufferBuilder(-5892809626544927852L));
        f.add(new BufferBuilder(-8451974756748527164L));
        f.add(new BufferBuilder(-7207548773927264842L));
        f.add(new BufferBuilder(6251354815369572362L));
        f.add(new BufferBuilder(-1741097637663778349L));
        f.add(new BufferBuilder(-1455699196600976679L));
        f.add(new BufferBuilder(3962492123447708411L));
        f.add(new BufferBuilder(-8343235660590081785L));
        f.add(new BufferBuilder(4854501433774555060L));
        f.add(new BufferBuilder(-1006775160805184546L));
        f.add(new BufferBuilder(8768356302166058583L));
        f.add(new BufferBuilder(-5670894312275186800L));
        f.add(new BufferBuilder(-957387463701784230L));
        f.add(new BufferBuilder(6795793872424428392L));
        f.add(new BufferBuilder(6680277039348179569L));
        f.add(new BufferBuilder(-3405180155750486128L));
        f.add(new BufferBuilder(3057723924304428272L));
        f.add(new BufferBuilder(8083942167017750512L));
        f.add(new BufferBuilder(-2737940618383116000L));
        f.add(new BufferBuilder(7045502413935400764L));
        f.add(new BufferBuilder(-6434528628234510374L));
        f.add(new BufferBuilder(-862295425977616044L));
        f.add(new BufferBuilder(1395771803559074046L));
        f.add(new BufferBuilder(-2730866161202372565L));
        f.add(new BufferBuilder(-7722642209356324973L));
        f.add(new BufferBuilder(2332215265890837285L));
        f.add(new BufferBuilder(-4253656950882455563L));
        f.add(new BufferBuilder(-7728192884731362617L));
        f.add(new BufferBuilder(3093059450706615432L));
        f.add(new BufferBuilder(-5735993243944070834L));
        f.add(new BufferBuilder(-6266069353599292116L));
        f.add(new BufferBuilder(-673340647682499633L));
        f.add(new BufferBuilder(7872798747974524270L));
        f.add(new BufferBuilder(8493520739996341774L));
        f.add(new BufferBuilder(5857253770843153373L));
        f.add(new BufferBuilder(-4765643959845499973L));
        f.add(new BufferBuilder(-6232708946981591178L));
        f.add(new BufferBuilder(3065826825390304272L));
        f.add(new BufferBuilder(1441037477329781369L));
        f.add(new BufferBuilder(4152762753096482725L));
        f.add(new BufferBuilder(-3897932891261772417L));
        f.add(new BufferBuilder(4939836025532366917L));
        f.add(new BufferBuilder(-8564937325964315121L));
        f.add(new BufferBuilder(189426204863350150L));
        f.add(new BufferBuilder(-2397670309091051061L));
        f.add(new BufferBuilder(3740872170489287438L));
        f.add(new BufferBuilder(3174528580908881835L));
        f.add(new BufferBuilder(-8472966096842777521L));
        f.add(new BufferBuilder(522332441238110122L));
        f.add(new BufferBuilder(-3570260265733238616L));
        f.add(new BufferBuilder(4343834606291111473L));
        f.add(new BufferBuilder(-6301920720324375875L));
        f.add(new BufferBuilder(-2002697860815070621L));
        f.add(new BufferBuilder(4039098239795458170L));
        f.add(new BufferBuilder(9088215209415693907L));
        f.add(new BufferBuilder(5383599228868490261L));
        f.add(new BufferBuilder(-4543056355689603646L));
        f.add(new BufferBuilder(803650443156070349L));
        f.add(new BufferBuilder(7933809019983005343L));
        f.add(new BufferBuilder(-3185550684862070952L));
        f.add(new BufferBuilder(8184939181750474030L));
        f.add(new BufferBuilder(-4229285685234962125L));
        f.add(new BufferBuilder(6925288150231779723L));
        f.add(new BufferBuilder(-594259770676744733L));
        f.add(new BufferBuilder(4805933878746116602L));
        f.add(new BufferBuilder(-1990415304951264198L));
        f.add(new BufferBuilder(-1300573969819467403L));
        f.add(new BufferBuilder(-5656272849585499630L));
        f.add(new BufferBuilder(2788663697590890289L));
        f.add(new BufferBuilder(872124729684145294L));
        f.add(new BufferBuilder(2782830030895018713L));
        f.add(new BufferBuilder(-5444779487771833916L));
        f.add(new BufferBuilder(-2292928071932837069L));
        f.add(new BufferBuilder(1690547361515928732L));
        e = (int[])e.clone();
        f.add(new BufferBuilder(4719169037526681117L));
        f.add(new BufferBuilder(-3256019495843030798L));
        f.add(new BufferBuilder(8370848702113378162L));
        f.add(new BufferBuilder(-3182816304933364074L));
        f.add(new BufferBuilder(-5536066397984171097L));
        f.add(new BufferBuilder(-4606541427073572774L));
        f.add(new BufferBuilder(-8531820942907454968L));
        f.add(new BufferBuilder(-2842302799697713057L));
        f.add(new BufferBuilder(5398531189571838586L));
        f.add(new BufferBuilder(-3360471561423308034L));
        f.add(new BufferBuilder(-1211326480497262408L));
        f.add(new BufferBuilder(-9177304161132158670L));
        f.add(new BufferBuilder(8536165228647253209L));
        f.add(new BufferBuilder(301667013962286619L));
        f.add(new BufferBuilder(3918335916533838169L));
        f.add(new BufferBuilder(-7264640875275267614L));
        f.add(new BufferBuilder(5326659848824823264L));
        f.add(new BufferBuilder(6272455313033724816L));
        f.add(new BufferBuilder(-2007142943078168786L));
        f.add(new BufferBuilder(-1762342400393967688L));
        f.add(new BufferBuilder(8204882385165282039L));
        f.add(new BufferBuilder(-3970639071513930466L));
        f.add(new BufferBuilder(-5909824587242363575L));
        f.add(new BufferBuilder(-7301531263734886490L));
        f.add(new BufferBuilder(2774258194185512091L));
        f.add(new BufferBuilder(-2316092914535840579L));
        f.add(new BufferBuilder(-8062975401052903667L));
        f.add(new BufferBuilder(1513688553973688576L));
        f.add(new BufferBuilder(-8048619942367478027L));
        f.add(new BufferBuilder(-8700452580139984744L));
        f.add(new BufferBuilder(4541323920961645235L));
        f.add(new BufferBuilder(5622477093704612171L));
        f.add(new BufferBuilder(-7529354852373567802L));
        f.add(new BufferBuilder(8692938983714204675L));
        f.add(new BufferBuilder(4320334541173692160L));
        f.add(new BufferBuilder(2165381819523911310L));
        f.add(new BufferBuilder(-5401466539777427883L));
        f.add(new BufferBuilder(-1457066280182390271L));
        f.add(new BufferBuilder(-6593225478385424258L));
        f.add(new BufferBuilder(3887757676308222485L));
        f.add(new BufferBuilder(4390247573865702185L));
        f.add(new BufferBuilder(-975531344639310538L));
        f.add(new BufferBuilder(-5322463644179199402L));
        f.add(new BufferBuilder(3304713002920425823L));
        f.add(new BufferBuilder(-5003170204534129545L));
        f.add(new BufferBuilder(-4737265935219864611L));
        f.add(new BufferBuilder(8167176918928909958L));
        f.add(new BufferBuilder(2091366035898471110L));
        f.add(new BufferBuilder(6028870184522288756L));
        f.add(new BufferBuilder(-8390803182440983692L));
        f.add(new BufferBuilder(-2194049687281186033L));
        f.add(new BufferBuilder(3764295413774586699L));
        f.add(new BufferBuilder(5090602499112393082L));
        f.add(new BufferBuilder(6204958381496205473L));
        f.add(new BufferBuilder(-483999320727586844L));
        f.add(new BufferBuilder(-4737901003089343178L));
        f.add(new BufferBuilder(-3819216775427432528L));
        f.add(new BufferBuilder(2603914573530791753L));
        f.add(new BufferBuilder(-4080449522620730798L));
        f.add(new BufferBuilder(7187986564409110307L));
        f.add(new BufferBuilder(1248207096013739096L));
        f.add(new BufferBuilder(1482935395900690164L));
        f.add(new BufferBuilder(-732250285299444104L));
        f.add(new BufferBuilder(-2777808176305580448L));
        f.add(new BufferBuilder(5685134678571647473L));
        f.add(new BufferBuilder(-4803955333969873251L));
        f.add(new BufferBuilder(-7218702004224467918L));
        f.add(new BufferBuilder(-408781639139341256L));
        f.add(new BufferBuilder(7423838660430720611L));
        f.add(new BufferBuilder(3186265442379620626L));
        f.add(new BufferBuilder(880982727147841540L));
        f.add(new BufferBuilder(-6652910649204114037L));
        f.add(new BufferBuilder(6421594837778346626L));
        f.add(new BufferBuilder(8734627079830095654L));
        f.add(new BufferBuilder(3401042475438664071L));
        f.add(new BufferBuilder(8686164524294400372L));
        f.add(new BufferBuilder(-7994008184575590198L));
        f.add(new BufferBuilder(1003726280887846557L));
        f.add(new BufferBuilder(-6633172495155185735L));
        f.add(new BufferBuilder(-4903634081229890392L));
        e = (int[])e.clone();
        f.add(new BufferBuilder(3810666062847284202L));
        f.add(new BufferBuilder(8193238053266081953L));
        f.add(new BufferBuilder(-910473303158080063L));
        f.add(new BufferBuilder(1510710320105582464L));
        f.add(new BufferBuilder(6119547849153084244L));
        f.add(new BufferBuilder(-9097080254616082596L));
        f.add(new BufferBuilder(2453309364982278915L));
        f.add(new BufferBuilder(1428864716851425489L));
        f.add(new BufferBuilder(834886697050471438L));
        f.add(new BufferBuilder(5628184341340421779L));
        f.add(new BufferBuilder(-225450230864496206L));
        f.add(new BufferBuilder(-5092323542095960086L));
        f.add(new BufferBuilder(-7258634621414054121L));
        f.add(new BufferBuilder(138488544827293748L));
        f.add(new BufferBuilder(7787387533426278490L));
        f.add(new BufferBuilder(2362544755208080360L));
        f.add(new BufferBuilder(-7651428790166718677L));
        f.add(new BufferBuilder(4706401009199834738L));
        f.add(new BufferBuilder(-8159027205701861011L));
        f.add(new BufferBuilder(4187024226679156932L));
        f.add(new BufferBuilder(-2251397477680174408L));
        f.add(new BufferBuilder(-1875109863369901574L));
        f.add(new BufferBuilder(3742504564061056353L));
        f.add(new BufferBuilder(5207670545388548668L));
        f.add(new BufferBuilder(-3543142568600086601L));
        f.add(new BufferBuilder(-5319778845379539590L));
        f.add(new BufferBuilder(-4327583043142017551L));
        f.add(new BufferBuilder(-6269875470200604504L));
        f.add(new BufferBuilder(2132491892759151713L));
        f.add(new BufferBuilder(-2190017132284774107L));
        f.add(new BufferBuilder(-2153235570788781483L));
        f.add(new BufferBuilder(1110451489463170035L));
        f.add(new BufferBuilder(-4863505189110815173L));
        f.add(new BufferBuilder(-4871499107408356459L));
        f.add(new BufferBuilder(-6751554858271831534L));
        f.add(new BufferBuilder(-8484665350853740103L));
        f.add(new BufferBuilder(1213466536913401524L));
        f.add(new BufferBuilder(9164678511328683350L));
        f.add(new BufferBuilder(-2294658575955335643L));
        f.add(new BufferBuilder(2983413820997007181L));
        f.add(new BufferBuilder(516269715986144493L));
        f.add(new BufferBuilder(1002902454721271606L));
        f.add(new BufferBuilder(-4756108639326659202L));
        f.add(new BufferBuilder(-1457344272118854601L));
        f.add(new BufferBuilder(-5855041488619967095L));
        f.add(new BufferBuilder(3788774842963954925L));
        f.add(new BufferBuilder(-4584006087905589620L));
        f.add(new BufferBuilder(8895608808092109166L));
        f.add(new BufferBuilder(-4520331482439903158L));
        f.add(new BufferBuilder(2915055581362826283L));
        f.add(new BufferBuilder(6124617638305744335L));
        f.add(new BufferBuilder(-5866354177327619872L));
        f.add(new BufferBuilder(-7119209777805569301L));
        f.add(new BufferBuilder(5524368387715549261L));
        f.add(new BufferBuilder(-3414880855938529228L));
        f.add(new BufferBuilder(-667829738203557157L));
        f.add(new BufferBuilder(8975470373520983923L));
        f.add(new BufferBuilder(-5294499619751781768L));
        f.add(new BufferBuilder(-4748445344093794681L));
        f.add(new BufferBuilder(3599351568937820734L));
        f.add(new BufferBuilder(4396325482560539220L));
        e = new int[]{
            -6,
            -60,
            -31,
            -21,
            -14,
            -36,
            6,
            -13,
            -47,
            -3,
            -17,
            -45,
            3,
            -4,
            -15,
            -4,
            -7,
            4,
            14,
            4,
            13,
            -24,
            -30,
            7,
            21,
            -29,
            -14,
            17,
            -16,
            15,
            -12,
            -1,
            1,
            31,
            -14,
            -2,
            -10,
            2,
            -9,
            -21,
            14,
            36,
            12,
            -16,
            16,
            24,
            10,
            9,
            14,
            -4,
            -12,
            -12,
            30,
            4,
            29,
            47,
            45,
            -1,
            1,
            16,
            21,
            60,
            12,
            12
        };
    }
}
