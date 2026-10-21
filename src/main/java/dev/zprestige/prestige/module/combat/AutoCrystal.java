package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.ColorSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.combat.CombatUtil;
import dev.zprestige.prestige.data.FloatData;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.FloatEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent5;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.inventory.InventoryUtil3;
import dev.zprestige.prestige.math.AutoCrystalHelper;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.type.EventPhase;
import dev.zprestige.prestige.world.PositionUtil;
import dev.zprestige.prestige.world.WorldUtil;
import dev.zprestige.prestige.world.WorldUtil2;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class AutoCrystal extends Module {
    public static boolean a = false;
    private BoolSetting d;
    private BoolSetting c;
    private BoolSetting e;
    private RangeSetting f;
    private BoolSetting g;
    private BoolSetting h;
    public BoolSetting i;
    public ColorSetting j;
    private BoolSetting k;
    private RenderHelper27 l = new RenderHelper27(26293358099357L);
    private BlockPos m = null;
    private ArrayDeque n = new ArrayDeque();
    private boolean o;
    private float p = 0.0F;
    private AutoCrystalHelper q = new AutoCrystalHelper(this, 108339993044693L);
    private boolean r = false;
    private int s = 0;
    private static final int t = 20;
    private static final boolean u = false;
    private static final boolean v = true;
    private static final boolean w = true;
    private static final boolean x = true;
    private static final float y = 0.0F;
    private static final float z = 200.0F;

    public AutoCrystal() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.j.setVisibility_ColorSetting(new Object[]{(Predicate<Color>)lpL221_0 -> (Boolean)this.i.getValue(), 119980880492450L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 29562578854912L;
        long var6 = var2 ^ 116279078700412L;
        a = true;
        this.r = false;
        this.s = 0;
        this.q.b(new Object[]{var6});
        Prestige.eventBus.register(new Object[]{this.q, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 138449290691138L;
        long var6 = var2 ^ 55259996380959L;
        a = false;
        this.r = false;
        this.n.clear();
        this.o = false;
        Prestige.eventBus.unregister(new Object[]{this.q, var4});
        this.q.b(new Object[]{var6});
        this.m = null;
    }

    @Override
    public void toggle(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 0L;
        long var6 = var2 ^ 26233901175331L;
        if (this.isEnabled()) {
            if (!this.r && (Boolean)this.k.getValue() && this.a(new Object[]{var6}) != null) {
                this.r = true;
                this.s = 0;
                return;
            }

            this.r = false;
        }

        super.toggle(new Object[]{var4});
    }

    private Entity a(Object[] var1) {
        if (b.crosshairTarget != null && b.crosshairTarget.getType() == Type.ENTITY) {
            Entity var5 = ((EntityHitResult)b.crosshairTarget).getEntity();
            if (!(var5 instanceof EndCrystalEntity) && !(var5 instanceof SlimeEntity) && !(var5 instanceof MagmaCubeEntity)) {
                return null;
            } else {
                return !var5.isAlive() ? null : var5;
            }
        } else {
            return null;
        }
    }

    private void j(Object[] var1) {
        FloatEvent2 var2 = (FloatEvent2)var1[0];
        long var3 = (Long)var1[1];
        var3 = 14307627693533L ^ var3;
        long var5 = var3 ^ 68331528291220L;
        long var7 = var3 ^ 12760787266702L;
        long var9 = var3 ^ 31091610975917L;
        if (var2.a_EventPhase(new Object[0]) != EventPhase.POST) {
            Entity var12 = this.a(new Object[]{var9});
            if (var12 == null || ++this.s > 20) {
                this.r = false;
                super.toggle(new Object[]{var7});
            } else if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem() && !Prestige.o.d_boolean______(new Object[0])) {
                var2.a_void_____(new Object[0]);
                float var10002 = var2.a(new Object[0]);
                float var10003 = var2.b_float__(new Object[0]);
                Object[] var10006 = new Object[]{null, null, null, var5};
                var10006[2] = var10003;
                var10006[1] = var10002;
                var10006[0] = var12;
                this.k(var10006);
                this.m = null;
                this.q.a(new Object[]{var12.getBlockPos().down()});
            }
        }
    }

    @Subscribe
    public void a(TickEvent5 var1) {
        if (this.getKey(new Object[]{13012393635966L}) == 9998 && b.player.getMainHandStack().getItem().equals(Items.END_CRYSTAL)) {
            var1.a_void_____(new Object[0]);
        }
    }

    private void k(Object[] var1) {
        Entity var5 = (Entity)var1[0];
        float var6 = (Float)var1[1];
        float var2 = (Float)var1[2];
        long var3 = (Long)var1[3];
        var3 = 14307627693533L ^ var3;
        long var7 = var3 ^ 84218717352013L;
        if (this.o) {
            if (this.n.size() < 4 && this.n.stream().noneMatch(lpL220_0 -> ((dev.zprestige.prestige.data.FloatData)lpL220_0).ag() == var5)) {
                this.n.add(new FloatData(var5, var6, var2));
            }
        } else {
            InventoryUtil2.a_void_________(new Object[]{var5, var7});
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (!this.n.isEmpty()) {
            if (!Prestige.o.d_boolean______(new Object[0])) {
                FloatData var7 = (FloatData)this.n.poll();
                if (var7 != null && var7.ag().isAlive()) {
                    Vec3d var8 = b.player.getEyePos();
                    Box var9 = var7.ag().getBoundingBox().expand(0.1);
                    Vec3d var10 = Vec3d.fromPolar(var7.ai(), var7.ah());
                    Optional var11 = var9.raycast(var8, var8.add(var10.multiply(3.0)));
                    if (!var11.isEmpty() && !(var8.distanceTo((Vec3d)var11.get()) > 2.9)) {
                        InventoryUtil2.a_void_________(new Object[]{var7.ag(), 31382773185830L});
                    }
                }
            }
        }
    }

    private static Vec3d a_class_243_____(Object[] var0) {
        Box var1 = (Box)var0[0];
        Vec3d var4 = (Vec3d)var0[1];
        Event.b();
        double var6 = MathHelper.clamp(var4.x, var1.minX, var1.maxX);
        double var8 = MathHelper.clamp(var4.y, var1.minY, var1.maxY);
        double var10 = MathHelper.clamp(var4.z, var1.minZ, var1.maxZ);
        Vec3d var12 = new Vec3d(var6, var8, var10);
        return var12.equals(var4) ? var1.getCenter() : var12;
    }

    @Subscribe
    public void k(FloatEvent2 var1) {
        if (var1.a_EventPhase(new Object[0]) == EventPhase.POST) {
            this.o = b.player != null && b.player.isSprinting();
        } else if (this.r) {
            this.j(new Object[]{var1, 121185753861887L});
        } else {
            if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem() && (b.player.isOnGround() || (Boolean)this.d.getValue())) {
                RenderHelper27 var10000 = this.l;
                float var10001 = (Float)this.f.getValue();
                Object[] var10004 = new Object[]{null, 2505922520844L};
                var10004[0] = var10001;
                if (var10000.a(var10004) && !AutoHitCrystal.a && !AutoObbyCrystal.R && var1.a_EventPhase(new Object[0]) != EventPhase.POST) {
                    if (Prestige.o.d_boolean______(new Object[0])) {
                        return;
                    }

                    if (b.crosshairTarget == null) {
                        return;
                    }

                    var1.a_void_____(new Object[0]);
                    if (!InventoryUtil.l(new Object[]{Items.END_CRYSTAL, 65539821781598L})) {
                        if ((Boolean)this.g.getValue()) {
                            Integer var67 = InventoryUtil.a_Integer(new Object[]{Items.END_CRYSTAL, 64376993919254L});
                            if (var67 == null) {
                                return;
                            }

                            int var71 = var67;
                            Object[] var76 = new Object[]{null, 37392877673079L};
                            var76[0] = var71;
                            InventoryUtil.a_void________(var76);
                            this.l.a_void_____________________(new Object[]{93540861878173L});
                            return;
                        }

                        return;
                    }

                    if (!this.l.b(new Object[]{this.f, 89714527855213L})) {
                        return;
                    }

                    this.l(new Object[]{125280375797500L});
                    PlayerEntity var45 = CombatUtil.a_class_1657(new Object[]{16945855905304L});
                    boolean var46 = this.a_boolean____________(new Object[]{var45, 60785806299468L});
                    if (var46) {
                        if (b.crosshairTarget.getType() == Type.ENTITY) {
                            EntityHitResult var47 = (EntityHitResult)b.crosshairTarget;
                            if (var47.getEntity() instanceof EndCrystalEntity
                                || var47.getEntity() instanceof SlimeEntity
                                || var47.getEntity() instanceof MagmaCubeEntity) {
                                if ((Boolean)this.e.getValue() && InventoryUtil3.a(new Object[]{var47.getEntity().getBlockPos(), 64853648109405L})) {
                                    return;
                                }

                                Entity var72 = var47.getEntity();
                                float var10002 = var1.a(new Object[0]);
                                float var10003 = var1.b_float__(new Object[0]);
                                Object[] var10006 = new Object[]{null, null, null, 102328345032886L};
                                var10006[2] = var10003;
                                var10006[1] = var10002;
                                var10006[0] = var72;
                                this.k(var10006);
                                if (var45 != null) {
                                    this.p = (float)Prestige.f
                                        .a(new Object[]{var45, PositionUtil.a(new Object[]{var47.getEntity(), 67736483458412L}), 81281073066560L});
                                }

                                this.m = null;
                                this.q.a(new Object[]{var47.getEntity().getBlockPos().down()});
                            }
                        } else if ((Boolean)this.h.getValue()) {
                            BlockPos var68 = this.m;
                            if (var68 == null && b.crosshairTarget.getType() == Type.BLOCK) {
                                var68 = ((BlockHitResult)b.crosshairTarget).getBlockPos();
                            }

                            if (var68 == null) {
                                return;
                            }

                            for (Entity var49 : b.world.getEntities()) {
                                if (var49 instanceof EndCrystalEntity) {
                                    Vec3d var50 = PositionUtil.a(new Object[]{var49, 67736483458412L});
                                    if (var50.distanceTo(var68.up().toCenterPos()) < 1.0) {
                                        Box var51 = var49.getBoundingBox();
                                        Vec3d var52 = b.player.getEyePos();
                                        double var53 = Math.max(var51.minX - var52.x, Math.max(0.0, var52.x - var51.maxX));
                                        double var55 = Math.max(var51.minY - var52.y, Math.max(0.0, var52.y - var51.maxY));
                                        double var57 = Math.max(var51.minZ - var52.z, Math.max(0.0, var52.z - var51.maxZ));
                                        double var59 = Math.sqrt(var53 * var53 + var55 * var55 + var57 * var57);
                                        if (var59 <= 2.7F
                                            && (!(Boolean)this.e.getValue() || !InventoryUtil3.a(new Object[]{var49.getBlockPos(), 64853648109405L}))) {
                                            Vec3d var61 = a_class_243_____(new Object[]{var51, var52, 19656243019732L});
                                            Vec3d var62 = var61.add(var51.getCenter().subtract(var61).multiply(0.3));
                                            float var70 = (float)var62.x;
                                            var10001 = (float)var62.y;
                                            float var74 = (float)var62.z;
                                            Object[] var10005 = new Object[]{null, null, null, 5229932684601L};
                                            var10005[2] = var74;
                                            var10005[1] = var10001;
                                            var10005[0] = var70;
                                            RenderBatch var63 = WorldUtil.b_RenderBatch_____________(var10005);
                                            float var64 = MathHelper.clamp(var63.b(new Object[0]), -89.9F, 89.9F);
                                            Vec3d var65 = Vec3d.fromPolar(var64, var1.a(new Object[0]));
                                            Optional var66 = var51.expand(0.1).raycast(var52, var52.add(var65.multiply(3.0)));
                                            if (!var66.isEmpty() && !(var52.distanceTo((Vec3d)var66.get()) > 2.9)) {
                                                var1.c(new Object[]{var64});
                                                var74 = var1.a(new Object[0]);
                                                Object[] var77 = new Object[]{null, null, null, 102328345032886L};
                                                var77[2] = var64;
                                                var77[1] = var74;
                                                var77[0] = var49;
                                                this.k(var77);
                                                if (var45 != null) {
                                                    this.p = (float)Prestige.f.a(new Object[]{var45, var50, 81281073066560L});
                                                }

                                                this.m = null;
                                                this.q.a(new Object[]{var49.getBlockPos().down()});
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (b.crosshairTarget.getType() == Type.BLOCK) {
                        BlockHitResult var69 = (BlockHitResult)b.crosshairTarget;
                        if (!WorldUtil2.k(new Object[]{var69.getBlockPos(), Blocks.BEDROCK, 137239425566158L})
                            && !WorldUtil2.k(new Object[]{var69.getBlockPos(), Blocks.OBSIDIAN, 137239425566158L})) {
                            return;
                        }

                        if (WorldUtil2.g(new Object[]{var69.getBlockPos().up(), 87119771691358L})
                            || !WorldUtil2.f(new Object[]{var69.getBlockPos(), 42392007623963L})) {
                            return;
                        }

                        if (!InventoryUtil.l(new Object[]{Items.END_CRYSTAL, 65539821781598L})) {
                            return;
                        }

                        InventoryUtil2.a(new Object[]{var69, 96519249476482L});
                        this.m = var69.getBlockPos();
                        this.q.a(new Object[]{var69.getBlockPos()});
                    }

                    return;
                }
            }
        }
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 14307627693533L ^ var2;
        long var4 = var2 ^ 46048232021180L;
        long var6 = var2 ^ 86968348659136L;
        this.f.reroll(new Object[]{var6});
        this.l.a_void_____________________(new Object[]{var4});
    }

    private boolean a_boolean____________(Object[] var1) {
        PlayerEntity var2 = (PlayerEntity)var1[0];
        long var3 = (Long)var1[1];
        var3 = 14307627693533L ^ var3;
        long var5 = var3 ^ 8592828418557L;
        long var7 = var3 ^ 127161969678033L;
        if ((Boolean)this.c.getValue() && var2 != null) {
            if (var2.hurtTime == 0) {
                this.p = 0.0F;
            }

            Vec3d var10 = Vec3d.ZERO;
            if (b.crosshairTarget instanceof BlockHitResult) {
                var10 = b.crosshairTarget.getPos().add(0.0, 1.0, 0.0);
            } else if (b.crosshairTarget instanceof EntityHitResult) {
                var10 = PositionUtil.a(new Object[]{((EntityHitResult)b.crosshairTarget).getEntity(), var5});
            }

            float var11 = (float)Prestige.f.a(new Object[]{var2, var10, var7});
            return !(var11 < this.p);
        } else {
            return true;
        }
    }

    private static Field g(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field h(Class var0, String var1, Class var2) {
        Field var3 = g(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = h(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method g(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method h(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = g(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = h(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }
}
