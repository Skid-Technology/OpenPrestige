package dev.zprestige.prestige.util;

import com.mojang.authlib.GameProfile;
import dev.zprestige.prestige.EventBus;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.RotationManager;
import dev.zprestige.prestige.config.ConfigUtil;
import dev.zprestige.prestige.data.MatrixCameraData;
import dev.zprestige.prestige.event.BlockBoolEvent;
import dev.zprestige.prestige.event.BlockPosBlockEvent;
import dev.zprestige.prestige.event.BoolEvent;
import dev.zprestige.prestige.event.ColorEvent;
import dev.zprestige.prestige.event.DoubleEvent;
import dev.zprestige.prestige.event.DoubleEvent2;
import dev.zprestige.prestige.event.DoubleEvent3;
import dev.zprestige.prestige.event.EntityEvent;
import dev.zprestige.prestige.event.EntityEvent2;
import dev.zprestige.prestige.event.EntityEvent3;
import dev.zprestige.prestige.event.EntityEvent4;
import dev.zprestige.prestige.event.EntityEvent5;
import dev.zprestige.prestige.event.EntityEvent6;
import dev.zprestige.prestige.event.FloatColorEvent;
import dev.zprestige.prestige.event.FloatDoubleEvent;
import dev.zprestige.prestige.event.FloatEvent;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.FloatEvent3;
import dev.zprestige.prestige.event.FloatEvent4;
import dev.zprestige.prestige.event.FloatEvent5;
import dev.zprestige.prestige.event.FloatPositionEvent;
import dev.zprestige.prestige.event.IntEvent;
import dev.zprestige.prestige.event.IntEvent2;
import dev.zprestige.prestige.event.IntEvent3;
import dev.zprestige.prestige.event.IntEvent4;
import dev.zprestige.prestige.event.LivingEvent;
import dev.zprestige.prestige.event.LongEvent;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.PlayerStringEvent;
import dev.zprestige.prestige.event.StringEvent;
import dev.zprestige.prestige.event.StringEvent2;
import dev.zprestige.prestige.event.TickEvent;
import dev.zprestige.prestige.event.TickEvent10;
import dev.zprestige.prestige.event.TickEvent11;
import dev.zprestige.prestige.event.TickEvent13;
import dev.zprestige.prestige.event.TickEvent14;
import dev.zprestige.prestige.event.TickEvent16;
import dev.zprestige.prestige.event.TickEvent17;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent2;
import dev.zprestige.prestige.event.TickEvent20;
import dev.zprestige.prestige.event.TickEvent21;
import dev.zprestige.prestige.event.TickEvent22;
import dev.zprestige.prestige.event.TickEvent23;
import dev.zprestige.prestige.event.TickEvent26;
import dev.zprestige.prestige.event.TickEvent28;
import dev.zprestige.prestige.event.TickEvent29;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.event.TickEvent30;
import dev.zprestige.prestige.event.TickEvent31;
import dev.zprestige.prestige.event.TickEvent4;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.event.TickEvent7;
import dev.zprestige.prestige.event.TickEvent8;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.network.PacketQueue;
import dev.zprestige.prestige.reflect.MethodHandleCache;
import dev.zprestige.prestige.render.ESPUtil;
import dev.zprestige.prestige.render.GlState;
import dev.zprestige.prestige.render.PostShader;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderHelper32;
import dev.zprestige.prestige.render.RenderHelper34;
import dev.zprestige.prestige.render.RenderHelper40;
import dev.zprestige.prestige.render.RenderLayerUtil;
import dev.zprestige.prestige.render.RenderUtil;
import dev.zprestige.prestige.render.Renderer2D;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldHelper7;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.ProjectionMatrix2;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.network.packet.Packet;
import net.minecraft.resource.Resource;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.WorldChunk;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;

public class ClientUtil {
    public static Resource dK(String var0) {
        return ConfigUtil.b(new Object[]{var0});
    }

    public static boolean ds(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static GlState eE() {
        return GlState.a();
    }

    public static InGameOverlayRenderer bR(RenderHelper32 var0) {
        return var0.a(new Object[]{107543254571096L});
    }

    public static Color m(FloatColorEvent var0) {
        return var0.a(new Object[0]);
    }

    public static boolean T(BlockBoolEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean cf(TickEvent17 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean bq() {
        return Prestige.b(new Object[]{79259704761476L});
    }

    public static RenderBatch cV(Renderer2D var0) {
        return var0.c(new Object[0]);
    }

    public static void bZ(WorldHelper7 var0, int var1) {
        Object[] var10004 = new Object[]{null, 50435661383866L};
        var10004[0] = var1;
        var0.a(var10004);
    }

    public static boolean dd(TickEvent19 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static DoubleEvent E(double var0, double var2, double var4) {
        return new DoubleEvent(var0, var2, var4);
    }

    public static boolean dn(TickEvent10 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static void K(MethodHandleCache var0, double var1, double var3, double var5) {
        Object[] var10006 = new Object[]{null, null, null, 2473080249125L};
        var10006[2] = var5;
        var10006[1] = var3;
        var10006[0] = var1;
        var0.a(var10006);
    }

    public static boolean dX(FloatDoubleEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static TickEvent28 dB() {
        return new TickEvent28();
    }

    public static boolean es(TickEvent23 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static BlockBoolEvent S(Block var0) {
        return new BlockBoolEvent(var0);
    }

    public static boolean cA(TickEvent16 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean dy(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static Vec3d aE(FloatPositionEvent var0) {
        return var0.a_class_243(new Object[0]);
    }

    public static boolean dl(TickEvent7 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static Predicate bX() {
        return FloatEvent4.a_Predicate(new Object[0]);
    }

    public static boolean cm(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static TickEvent19 da() {
        return new TickEvent19();
    }

    public static BoolEvent aj(EventPhase var0) {
        return new BoolEvent(var0);
    }

    public static boolean bi(EntityEvent5 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean dw(IntEvent4 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static TickEvent29 dg(Window var0) {
        return new TickEvent29(var0);
    }

    public static boolean ax(EntityEvent3 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static Optional dq(RenderLayer var0) {
        return RenderLayerUtil.a(new Object[]{var0, 48751301194998L});
    }

    public static Color dZ(int var0, int var1, int var2) {
        return new Color(var0, var1, var2);
    }

    public static TickEvent11 cv() {
        return new TickEvent11();
    }

    public static BoolEvent ag(EventPhase var0) {
        return new BoolEvent(var0);
    }

    public static Vec3d aF(FloatPositionEvent var0) {
        return var0.a_class_243(new Object[0]);
    }

    public static TickEvent22 eN(Frustum var0) {
        return new TickEvent22(var0);
    }

    public static boolean dY(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean bg(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static Resource dI(Identifier var0) {
        return ConfigUtil.a(new Object[]{var0});
    }

    public static float ar(FloatEvent2 var0) {
        return var0.b_float__(new Object[0]);
    }

    public static boolean R(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static FloatColorEvent v(float var0, float var1, Color var2) {
        return new FloatColorEvent(var0, var1, var2);
    }

    public static RenderHelper34 by(GuiRenderer var0) {
        return new RenderHelper34(var0);
    }

    public static EntityEvent au(Entity var0) {
        return new EntityEvent(var0);
    }

    public static float t(FloatColorEvent var0) {
        return var0.b_float(new Object[0]);
    }

    public static boolean en(StringEvent2 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static TickEvent21 dz() {
        return new TickEvent21();
    }

    public static MatrixStack bI() {
        return new MatrixStack();
    }

    public static boolean ci(TickEvent14 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static TickEvent5 di() {
        return new TickEvent5();
    }

    public static boolean aC(FloatPositionEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean cd(FloatEvent5 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean dh(TickEvent29 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean aJ(EntityEvent2 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean bl(PlayerStringEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static double aO(DoubleEvent3 var0) {
        return var0.b_double_(new Object[0]);
    }

    public static DoubleEvent3 aL(double var0, double var2, double var4) {
        return new DoubleEvent3(var0, var2, var4);
    }

    public static boolean du(DoubleEvent2 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static RenderBatch aA(Renderer2D var0) {
        return var0.c(new Object[0]);
    }

    public static boolean F(DoubleEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean bH() {
        return Prestige.b(new Object[]{79259704761476L});
    }

    public static SkinTextures g(StringEvent var0) {
        return var0.a(new Object[0]);
    }

    public static TickEvent17 ce() {
        return new TickEvent17();
    }

    public static IntEvent4 dv(int var0, int var1) {
        return new IntEvent4(var0, var1);
    }

    public static boolean cb(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean cG(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static FloatEvent3 cN(EventPhase var0, float var1, float var2) {
        return new FloatEvent3(var0, var1, var2);
    }

    public static EntityEvent6 bn(Text var0, Text var1, Entity var2) {
        return new EntityEvent6(var0, var1, var2);
    }

    public static TickEvent31 ck() {
        return new TickEvent31();
    }

    public static void bw() {
        Prestige.a_void(new Object[]{132410135119224L});
    }

    public static WorldHelper7 bY(Object var0) {
        return new WorldHelper7(var0);
    }

    public static FloatDoubleEvent dW(float var0, double var1, double var3, double var5) {
        return new FloatDoubleEvent(var0, var1, var3, var5);
    }

    public static Color l(FloatColorEvent var0) {
        return var0.a(new Object[0]);
    }

    public static void eF(List var0) {
        RenderUtil.c_void__(new Object[]{var0, 10065397776691L});
    }

    public static RenderBatch aT(Renderer2D var0) {
        return var0.b_RenderBatch____________(new Object[0]);
    }

    public static Resource dL(String var0) {
        return ConfigUtil.b(new Object[]{var0});
    }

    public static float aR(RenderBatch var0) {
        return var0.b(new Object[0]);
    }

    public static boolean c(BlockBoolEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean cU(LivingEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean cP(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static TickEvent10 dm() {
        return new TickEvent10();
    }

    public static TickEvent30 eQ() {
        return new TickEvent30();
    }

    public static boolean U(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static String dE(GameProfile var0) {
        return PositionUtil.a_String________(new Object[]{var0, 3044946865408L});
    }

    public static boolean M(FloatEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static TickEvent7 dk() {
        return new TickEvent7();
    }

    public static Vec3d aG(double var0, double var2, double var4) {
        return new Vec3d(var0, var2, var4);
    }

    public static String eo(StringEvent2 var0) {
        return var0.a(new Object[0]);
    }

    public static boolean cI(TickEvent2 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean df(TickEvent4 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean aX(RotationManager var0) {
        return var0.a(new Object[0]);
    }

    public static Resource dx(Identifier var0) {
        return ConfigUtil.a(new Object[]{var0});
    }

    public static double I(DoubleEvent var0) {
        return var0.b_double(new Object[0]);
    }

    public static boolean ct(TickEvent11 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean cy(IntEvent2 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static TickEvent16 cz(EventPhase var0) {
        return new TickEvent16(var0);
    }

    public static ColorEvent eb(Color var0, Color var1) {
        return new ColorEvent(var0, var1);
    }

    public static boolean cL(FloatEvent3 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static float cY(FloatPositionEvent var0) {
        return var0.a(new Object[0]);
    }

    public static TickEvent11 cs() {
        return new TickEvent11();
    }

    public static List eA() {
        return RenderUtil.a_List__(new Object[]{118978392460632L});
    }

    public static boolean dj(TickEvent5 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean eM(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static void eL(GlState var0) {
        var0.a_void();
    }

    public static boolean at(FloatEvent2 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean f(StringEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static GlState bt() {
        return GlState.a();
    }

    public static boolean et(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static TickEvent2 bc() {
        return new TickEvent2();
    }

    public static boolean af(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static MatrixCameraData eI(Matrix4f var0, Matrix4f var1, Camera var2) {
        return new MatrixCameraData(var0, var1, var2);
    }

    public static TickEvent13 ad(WorldChunk var0) {
        return new TickEvent13(var0);
    }

    public static PlayerInput cE(boolean var0, boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6) {
        return new PlayerInput(var0, var1, var2, var3, var4, var5, var6);
    }

    public static boolean bS(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static TickEvent14 ch() {
        return new TickEvent14();
    }

    public static EntityEvent4 aZ(Entity var0, EventPhase var1) {
        return new EntityEvent4(var0, var1);
    }

    public static void bF(GlState var0) {
        var0.a_void();
    }

    public static int bP(IntEvent var0) {
        return var0.a(new Object[0]);
    }

    public static Resource dN(String var0) {
        return ConfigUtil.b(new Object[]{var0});
    }

    public static FloatEvent2 al(EventPhase var0, float var1, float var2) {
        return new FloatEvent2(var0, var1, var2);
    }

    public static TickEvent4 de() {
        return new TickEvent4();
    }

    public static boolean eq(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean cu(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean eC() {
        return Prestige.b(new Object[]{79259704761476L});
    }

    public static FloatPositionEvent cW(float var0) {
        return new FloatPositionEvent(var0);
    }

    public static boolean aU(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean d(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static StringEvent2 em(String var0, String var1) {
        return new StringEvent2(var0, var1);
    }

    public static Color k(FloatColorEvent var0) {
        return var0.a(new Object[0]);
    }

    public static boolean eh(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean dA(TickEvent21 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static Resource dP(String var0) {
        return ConfigUtil.b(new Object[]{var0});
    }

    public static TickEvent20 bK() {
        return new TickEvent20();
    }

    public static Resource dJ(String var0) {
        return ConfigUtil.b(new Object[]{var0});
    }

    public static boolean u(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean ej(EntityEvent5 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean bM(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static IntEvent3 cQ(int var0) {
        return new IntEvent3(var0);
    }

    public static EntityEvent4 aV(Entity var0, EventPhase var1) {
        return new EntityEvent4(var0, var1);
    }

    public static String ep(StringEvent2 var0) {
        return var0.b_String_(new Object[0]);
    }

    public static boolean cl(TickEvent31 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static VertexConsumer dr(VertexConsumer var0, int var1) {
        Object[] var10004 = new Object[]{null, null, 50226393134120L};
        var10004[1] = var1;
        var10004[0] = var0;
        return RenderLayerUtil.a_class_4588(var10004);
    }

    public static boolean bb(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static float ap(RenderBatch var0) {
        return var0.b(new Object[0]);
    }

    public static EntityEvent5 bh(Entity var0, Text var1) {
        return new EntityEvent5(var0, var1);
    }

    public static TickEvent23 er() {
        return new TickEvent23();
    }

    public static boolean be(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean ah(BoolEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static PlayerStringEvent bk(PlayerEntity var0, String var1) {
        return new PlayerStringEvent(var0, var1);
    }

    public static Vector4f o(float var0, float var1, float var2, float var3) {
        return new Vector4f(var0, var1, var2, var3);
    }

    public static boolean eP(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static RenderHelper32 bQ(GameRenderer var0) {
        return new RenderHelper32(var0);
    }

    public static BlockPosBlockEvent ey(BlockPos var0, BlockState var1) {
        return new BlockPosBlockEvent(var0, var1);
    }

    public static void bx(GlState var0) {
        var0.a_void();
    }

    public static void eK(GlState var0) {
        var0.a_void();
    }

    public static float O(FloatEvent var0) {
        return var0.a(new Object[0]);
    }

    public static FloatEvent3 cK(EventPhase var0, float var1, float var2) {
        return new FloatEvent3(var0, var1, var2);
    }

    public static TickEvent2 cH() {
        return new TickEvent2();
    }

    public static Text ek(EntityEvent5 var0) {
        return var0.a_class_2561(new Object[0]);
    }

    public static String bm(PlayerStringEvent var0) {
        return var0.a_String__(new Object[0]);
    }

    public static FloatEvent4 bU(float var0) {
        return new FloatEvent4(var0);
    }

    public static boolean B(BlockBoolEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean cq(TickEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean ez(BlockPosBlockEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean aY(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean C(BlockBoolEvent var0) {
        return var0.c(new Object[0]);
    }

    public static MatrixCameraData bD(Matrix4f var0, Matrix4f var1, Camera var2) {
        return new MatrixCameraData(var0, var1, var2);
    }

    public static boolean cX(FloatPositionEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean cw(TickEvent11 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static Color ea(int var0, int var1, int var2) {
        return new Color(var0, var1, var2);
    }

    public static TickEvent26 cn() {
        return new TickEvent26();
    }

    public static double H(DoubleEvent var0) {
        return var0.a(new Object[0]);
    }

    public static double J(DoubleEvent var0) {
        return var0.c_double(new Object[0]);
    }

    public static RenderHelper40 bA(ProjectionMatrix2 var0) {
        return new RenderHelper40(var0);
    }

    public static Matrix4f eH(Matrix4fc var0) {
        return new Matrix4f(var0);
    }

    public static boolean dT(TickEvent8 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static PostShader do_() {
        return ESPUtil.a_PostShader(new Object[0]);
    }

    public static Resource dR(String var0) {
        return ConfigUtil.b(new Object[]{var0});
    }

    public static boolean cC(TickEvent16 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static FloatColorEvent i(float var0, float var1, Color var2) {
        return new FloatColorEvent(var0, var1, var2);
    }

    public static BlockBoolEvent A(Block var0) {
        return new BlockBoolEvent(var0);
    }

    public static boolean db(TickEvent19 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static RenderBatch aQ(Renderer2D var0) {
        return var0.c(new Object[0]);
    }

    public static float aD(FloatPositionEvent var0) {
        return var0.a(new Object[0]);
    }

    public static boolean aH(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static TickEvent cp() {
        return new TickEvent();
    }

    public static boolean ec(ColorEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static void br() {
        RenderUtil.h(new Object[]{13441578023978L});
    }

    public static float r(FloatColorEvent var0) {
        return var0.b_float(new Object[0]);
    }

    public static void aa(PacketQueue var0, Packet var1) {
        var0.a_void___________(new Object[]{var1, 42438847979216L});
    }

    public static boolean am(FloatEvent2 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static float bW(FloatEvent4 var0) {
        return var0.a(new Object[0]);
    }

    public static Vector4f y(float var0, float var1, float var2, float var3) {
        return new Vector4f(var0, var1, var2, var3);
    }

    public static boolean cF(PacketQueue var0) {
        return var0.c_boolean___(new Object[]{46693235234696L});
    }

    public static void ca(WorldHelper7 var0, int var1) {
        Object[] var10004 = new Object[]{null, 113610767558335L};
        var10004[0] = var1;
        var0.b(var10004);
    }

    public static boolean aW(EntityEvent4 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean cr(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean W(PacketEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static LongEvent eu(long var0) {
        return new LongEvent(var0);
    }

    public static boolean w(FloatColorEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static void eJ(MatrixCameraData var0) {
        RenderUtil.d(new Object[]{var0, 65713249368887L});
    }

    public static void eG(GlState var0) {
        var0.a_void();
    }

    public static EntityEvent2 aI(Entity var0, Box var1) {
        return new EntityEvent2(var0, var1);
    }

    public static FloatEvent L(float var0, float var1) {
        return new FloatEvent(var0, var1);
    }

    public static boolean h(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static Text bj(EntityEvent5 var0) {
        return var0.a_class_2561(new Object[0]);
    }

    public static PacketEvent2 Y(Packet var0) {
        return new PacketEvent2(var0);
    }

    public static boolean dD(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static FloatEvent5 cc(MatrixStack var0, Arm var1, float var2, float var3) {
        return new FloatEvent5(var0, var1, var2, var3);
    }

    public static ProjectionMatrix2 bz(RenderHelper34 var0) {
        return var0.a(new Object[]{54366425469524L});
    }

    public static TickEvent19 dc() {
        return new TickEvent19();
    }

    public static boolean ae(TickEvent13 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean ev(LongEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static Matrix4f bC() {
        return new Matrix4f();
    }

    public static boolean bf(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static MethodHandleCache N(Camera var0) {
        return new MethodHandleCache(var0);
    }

    public static Color ed(ColorEvent var0) {
        return var0.a(new Object[0]);
    }

    public static DoubleEvent2 dt(double var0, double var2) {
        return new DoubleEvent2(var0, var2);
    }

    public static Color eg(ColorEvent var0) {
        return var0.b_Color(new Object[0]);
    }

    public static EntityEvent5 ei(Entity var0, Text var1) {
        return new EntityEvent5(var0, var1);
    }

    public static Color ee(ColorEvent var0) {
        return var0.a(new Object[0]);
    }

    public static void eD() {
        dev.zprestige.prestige.render.MatrixStack.a(new Object[]{14373979246969L});
    }

    public static boolean az(EntityEvent3 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static PacketEvent V(Packet var0) {
        return new PacketEvent(var0);
    }

    public static boolean ac(TickEvent3 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static LivingEvent cT(LivingEntity var0) {
        return new LivingEvent(var0);
    }

    public static boolean bJ(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static IntEvent2 cx(int var0, int var1) {
        return new IntEvent2(var0, var1);
    }

    public static Color x(FloatColorEvent var0) {
        return var0.a(new Object[0]);
    }

    public static boolean bO(IntEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static Matrix4f bB(RenderHelper40 var0, float var1, float var2) {
        Object[] var10005 = new Object[]{null, null, 4953355148731L};
        var10005[1] = var2;
        var10005[0] = var1;
        return var0.a(var10005);
    }

    public static boolean cD(PacketQueue var0) {
        return var0.b_boolean______(new Object[]{57447699507603L});
    }

    public static Float p(FloatColorEvent var0) {
        return var0.a_Float(new Object[0]);
    }

    public static boolean dp(EntityModel var0) {
        return ESPUtil.c(new Object[]{var0, 4971385859609L});
    }

    public static boolean Z(PacketEvent2 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static MethodHandleCache G(Camera var0) {
        return new MethodHandleCache(var0);
    }

    public static boolean cR(IntEvent3 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static TickEvent16 cB(EventPhase var0) {
        return new TickEvent16(var0);
    }

    public static BlockBoolEvent b(Block var0) {
        return new BlockBoolEvent(var0);
    }

    public static boolean cS(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static float aS(RenderBatch var0) {
        return var0.a(new Object[0]);
    }

    public static boolean aM(DoubleEvent3 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean ai(BoolEvent var0) {
        return var0.c(new Object[0]);
    }

    public static void bv() {
        RenderUtil.h(new Object[]{13441578023978L});
    }

    public static boolean D(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static Resource dO(String var0) {
        return ConfigUtil.b(new Object[]{var0});
    }

    public static SkinTextures dH(StringEvent var0) {
        return var0.a(new Object[0]);
    }

    public static boolean ex(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean cO(FloatEvent3 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static void bs() {
        Prestige.a_void(new Object[]{132410135119224L});
    }

    public static void bu(List var0) {
        RenderUtil.c_void__(new Object[]{var0, 10065397776691L});
    }

    public static boolean dV(TickEvent8 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static TickEvent8 dU() {
        return new TickEvent8();
    }

    public static IntEvent bN(int var0) {
        return new IntEvent(var0);
    }

    public static boolean ba(EntityEvent4 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean co(TickEvent26 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static EntityEvent3 ay(Entity var0, EventPhase var1) {
        return new EntityEvent3(var0, var1);
    }

    public static boolean eO(TickEvent22 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static float bT() {
        return InventoryUtil2.b_float_____(new Object[]{18814856656518L});
    }

    public static boolean cg(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean a(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static Resource dM(String var0) {
        return ConfigUtil.b(new Object[]{var0});
    }

    public static void bG(GlState var0) {
        var0.a_void();
    }

    public static TickEvent3 ab() {
        return new TickEvent3();
    }

    public static double aP(DoubleEvent3 var0) {
        return var0.c_double_(new Object[0]);
    }

    public static RenderBatch cJ(Renderer2D var0) {
        return var0.c(new Object[0]);
    }

    public static List bp() {
        return RenderUtil.a_List__(new Object[]{118978392460632L});
    }

    public static float s(FloatColorEvent var0) {
        return var0.a_float____(new Object[0]);
    }

    public static Color ef(ColorEvent var0) {
        return var0.b_Color(new Object[0]);
    }

    public static StringEvent dF(SkinTextures var0, String var1) {
        return new StringEvent(var0, var1);
    }

    public static float aq(FloatEvent2 var0) {
        return var0.a(new Object[0]);
    }

    public static boolean bL(TickEvent20 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static StringEvent e(SkinTextures var0, String var1) {
        return new StringEvent(var0, var1);
    }

    public static void Q(MethodHandleCache var0, float var1, float var2) {
        Object[] var10005 = new Object[]{null, null, 32803728178790L};
        var10005[1] = var2;
        var10005[0] = var1;
        var0.b(var10005);
    }

    public static EntityEvent3 aw(Entity var0, EventPhase var1) {
        return new EntityEvent3(var0, var1);
    }

    public static boolean dG(StringEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean bd(TickEvent2 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static float q(FloatColorEvent var0) {
        return var0.a_float____(new Object[0]);
    }

    public static boolean bo(EntityEvent6 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static float ao(RenderBatch var0) {
        return var0.a(new Object[0]);
    }

    public static long ew(LongEvent var0) {
        return var0.a(new Object[0]);
    }

    public static boolean cZ(EntityModel var0) {
        return ESPUtil.b(new Object[]{var0, 112928708948977L});
    }

    public static boolean dC(TickEvent28 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static RenderBatch an(Renderer2D var0) {
        return var0.d(new Object[]{74534716520795L});
    }

    public static boolean X(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean j(FloatColorEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static void bE(MatrixCameraData var0) {
        RenderUtil.e(new Object[]{var0, 130867719496852L});
    }

    public static boolean cj(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean eB() {
        return Prestige.a(new Object[]{109396286056715L});
    }

    public static boolean bV(FloatEvent4 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static Resource dQ(String var0) {
        return ConfigUtil.b(new Object[]{var0});
    }

    public static Color n(FloatColorEvent var0) {
        return var0.a(new Object[0]);
    }

    public static FloatPositionEvent aB(float var0, Vec3d var1) {
        return new FloatPositionEvent(var0, var1);
    }

    public static boolean el(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static boolean av(EntityEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static boolean ak(BoolEvent var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static double aN(DoubleEvent3 var0) {
        return var0.a(new Object[0]);
    }

    public static TickEvent8 dS() {
        return new TickEvent8();
    }

    public static FloatEvent2 as(EventPhase var0, float var1, float var2) {
        return new FloatEvent2(var0, var1, var2);
    }

    public static boolean eR(TickEvent30 var0) {
        return var0.b_boolean__(new Object[]{122499962143880L});
    }

    public static float P(FloatEvent var0) {
        return var0.b_float_(new Object[0]);
    }

    public static boolean z(EventBus var0, Class var1) {
        return var0.hasListeners(new Object[]{var1, 135978089436075L});
    }

    public static Box aK(EntityEvent2 var0) {
        return var0.a_class_238(new Object[0]);
    }

    public static RenderBatch cM(Renderer2D var0) {
        return var0.c(new Object[0]);
    }

    private static Field a(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field b(Class var0, String var1, Class var2) {
        Field var3 = a(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = b(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method a(Class var0, String var1, Class var2, int var3, Class[] var4) {
        label33:
        for (Method var8 : var0.getDeclaredMethods()) {
            if (var8.getName().equals(var1) && var8.getReturnType() == var2) {
                Class[] var9 = var8.getParameterTypes();
                if (var9.length == var3) {
                    for (int var10 = 0; var10 < var3; var10++) {
                        if (var9[var10] != var4[var10]) {
                            continue label33;
                        }
                    }

                    return var8;
                }
            }
        }

        return null;
    }

    private static Method b(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = a(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = b(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
