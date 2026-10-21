package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderBatch;
import dev.zprestige.prestige.render.RenderContract3;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.render.Renderer2D;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.type.ScoopState;
import dev.zprestige.prestige.world.WorldUtil;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class AntiWeb extends Module implements RenderContract3 {
    private EnumSetting a;
    private RangeSetting c;
    private EnumSetting d;
    private BoolSetting e;
    private BoolSetting f;
    private static final int g = 40;
    private static final int h = 6;
    private ScoopState i = ScoopState.IDLE;
    private BlockPos j;
    private BlockPos k;
    private int l = -1;
    private int m;
    private int n;
    private RenderHelper27 o = new RenderHelper27(26293358099357L);
    private RenderHelper27 p = new RenderHelper27(26293358099357L);

    public AntiWeb() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.e.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL58_0 -> ((String)this.d.getValue()).equals("Normal"), 73196168581295L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.UHC);
    }

    @Override
    public void onEnable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 91082959635357L;
        long var6 = var2 ^ 120394196241246L;
        this.j(new Object[]{var6});
        Prestige.o.e(new Object[]{this, var4});
    }

    @Override
    public void onDisable(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 82893747353842L;
        long var6 = var2 ^ 140372736859409L;
        long var8 = var2 ^ 6195065515282L;
        long var10 = var2 ^ 60456363807037L;
        if ((Boolean)this.e.getValue() && this.l != -1) {
            if (Prestige.o.d_boolean______(new Object[0])) {
                Renderer2D var10000 = Prestige.o;
                int var10001 = this.l;
                Object[] var10004 = new Object[]{null, var4};
                var10004[0] = var10001;
                var10000.i(var10004);
            } else {
                int var12 = this.l;
                Object[] var10003 = new Object[]{null, var8};
                var10003[0] = var12;
                InventoryUtil.a_void________(var10003);
            }

            this.l = -1;
        }

        this.j(new Object[]{var10});
        Prestige.o.f(new Object[]{this, var6});
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.j(new Object[]{19088102123096L});
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 90137803451192L ^ var2;
        long var4 = var2 ^ 23870112436989L;
        long var6 = var2 ^ 126770868568961L;
        this.i = ScoopState.IDLE;
        this.j = null;
        this.k = null;
        this.m = 0;
        this.n = 0;
        this.o.a_void_____________________(new Object[]{var4});
        this.c.reroll(new Object[]{var6});
    }

    private void k(Object[] var1) {
        String var4 = (String)var1[0];
        long var2 = (Long)var1[1];
        var2 = 90137803451192L ^ var2;
        long var5 = var2 ^ 28750511922225L;
        long var7 = var2 ^ 46345826032040L;
        long var9 = var2 ^ 137932391866681L;
        RenderHelper27 var10000 = this.p;
        Object[] var10004 = new Object[]{null, var9};
        var10004[0] = 3000.0F;
        if (var10000.a(var10004)) {
            ModuleNotificationsElement.s(new Object[]{this, var4, NotificationType.INFO, var5});
            this.p.a_void_____________________(new Object[]{var7});
        }
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.player == null || b.world == null) {
            this.j(new Object[]{19088102123096L});
        } else if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem()) {
            if (!Prestige.o.d_boolean______(new Object[0])) {
                switch (this.i) {
                    case IDLE:
                        this.l(new Object[]{16015249410174L});
                    case PLACE:
                    case SCOOP:
                    default:
                        break;
                    case WAIT:
                        if (this.d(new Object[]{73911195020891L})) {
                            this.i = ScoopState.SCOOP;
                            this.o.a_void_____________________(new Object[]{93540861878173L});
                            this.c.reroll(new Object[]{57083216809697L});
                        } else if (++this.m > 40) {
                            this.k(new Object[]{"The water never cleared the web.", 51467673545997L});
                            this.o(new Object[]{133214379963576L});
                        }
                        break;
                    case BACK:
                        if (!this.o.b(new Object[]{this.c, 89714527855213L})) {
                            return;
                        }

                        if ((Boolean)this.e.getValue() && this.l != -1 && !Prestige.o.d_boolean______(new Object[0])) {
                            int var10000 = this.l;
                            Object[] var10003 = new Object[]{null, 37392877673079L};
                            var10003[0] = var10000;
                            InventoryUtil.a_void________(var10003);
                            this.l = -1;
                        }

                        this.j(new Object[]{19088102123096L});
                }

                if (((String)this.a.getValue()).equalsIgnoreCase("Legit")) {
                    this.m(new Object[]{69227916348014L});
                }
            }
        }
    }

    private void l(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 90137803451192L ^ var2;
        long var4 = var2 ^ 11515568192731L;
        long var6 = var2 ^ 112042532622928L;
        long var8 = var2 ^ 124954572475979L;
        long var10 = var2 ^ 119726196267602L;
        long var12 = var2 ^ 119300104760743L;
        Event.b();
        BlockPos var15 = b.player.getBlockPos();
        BlockPos var16 = null;
        if (this.a_boolean_________(new Object[]{var15.down(), var10})) {
            var16 = var15.down();
        } else if (this.a_boolean_________(new Object[]{var15, var10})) {
            var16 = var15;
        } else if (this.a_boolean_________(new Object[]{var15.up(), var10})) {
            var16 = var15.up();
        }

        if (var16 == null && (Boolean)this.f.getValue()) {
            Box var17 = b.player.getBoundingBox();

            for (int var18 = 0; var18 <= 1 && var16 == null; var18++) {
                for (int var19 = -1; var19 <= 1 && var16 == null; var19++) {
                    for (int var20 = -1; var20 <= 1; var20++) {
                        if (var19 != 0 || var20 != 0) {
                            BlockPos var21 = var15.add(var19, var18, var20);
                            if (this.a_boolean_________(new Object[]{var21, var10})
                                && var17.maxX > var21.getX()
                                && var17.minX < var21.getX() + 1
                                && var17.maxZ > var21.getZ()
                                && var17.minZ < var21.getZ() + 1
                                && var17.maxY > var21.getY()
                                && var17.minY < var21.getY() + 1) {
                                var16 = var21;
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (var16 != null) {
            this.k = var16;
            this.j = var16;

            while (this.a_boolean_________(new Object[]{this.j.up(), var10})) {
                this.j = this.j.up();
            }

            if (((String)this.d.getValue()).equals("Normal") && InventoryUtil.a_Integer(new Object[]{Items.WATER_BUCKET, var6}) == null) {
                this.k(new Object[]{"No water bucket in hotbar.", var8});
            } else if (b.world.getFluidState(this.j.up()).isStill()) {
                this.i = ScoopState.WAIT;
                this.m = 0;
            } else if (!b.world.getBlockState(this.j.up()).isReplaceable()) {
                this.k(new Object[]{"No room above the web for water.", var8});
            } else {
                this.i = ScoopState.PLACE;
                this.m = 0;
                this.n = 0;
                this.o.a_void_____________________(new Object[]{var4});
                this.c.reroll(new Object[]{var12});
            }
        }
    }

    private boolean a_boolean_________(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        return var2 != null && b.world.getBlockState(var2).getBlock() == Blocks.COBWEB;
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 90137803451192L ^ var2;
        long var4 = var2 ^ 36610043329655L;
        Event.b();

        for (BlockPos var7 = this.k; !this.a_boolean_________(new Object[]{var7, var4}); var7 = var7.up()) {
            if (var7.equals(this.j)) {
                return true;
            }
        }

        return false;
    }

    private Vec3d a_class_243___(Object[] var1) {
        BlockPos var2 = (BlockPos)var1[0];
        float var5 = (float)Math.toRadians(b.player.getYaw());
        return var2.toCenterPos().add(-Math.sin(var5) * 0.25, 0.0, Math.cos(var5) * 0.25);
    }

    private void m(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 90137803451192L ^ var2;
        long var4 = var2 ^ 63907423436491L;
        long var6 = var2 ^ 58332818276040L;
        long var8 = var2 ^ 72309918108763L;
        long var10 = var2 ^ 68809604842299L;
        long var12 = var2 ^ 101727835150402L;
        long var14 = var2 ^ 137500873033332L;
        long var16 = var2 ^ 102143151435703L;
        long var18 = var2 ^ 98424680506063L;
        long var20 = var2 ^ 478739912118L;
        switch (this.i) {
            case PLACE:
                if (!this.o.b(new Object[]{this.c, var10})) {
                    return;
                }

                if (!this.a_boolean_________(new Object[]{this.j, var12})) {
                    if (b.world.getFluidState(this.j.up()).isStill()) {
                        this.i = ScoopState.WAIT;
                        this.m = 0;
                        this.o.a_void_____________________(new Object[]{var4});
                        this.c.reroll(new Object[]{var16});
                    } else {
                        this.n(new Object[]{var20});
                    }

                    return;
                }

                if (!this.g(new Object[]{Items.WATER_BUCKET, var18})) {
                    return;
                }

                if (!(b.crosshairTarget instanceof BlockHitResult var23) || !var23.getBlockPos().equals(this.j)) {
                    return;
                }

                if (InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var14})) {
                    this.i = ScoopState.WAIT;
                    this.m = 0;
                    this.o.a_void_____________________(new Object[]{var4});
                    this.c.reroll(new Object[]{var16});
                }
                break;
            case SCOOP:
                if (!this.o.b(new Object[]{this.c, var10})) {
                    return;
                }

                if (!this.g(new Object[]{Items.BUCKET, var18})) {
                    return;
                }

                if (!b.world.getFluidState(this.j.up()).isStill()) {
                    this.n(new Object[]{var20});
                    return;
                }

                if (this.e(new Object[]{this.j.up().toCenterPos(), var6})) {
                    this.n(new Object[]{var20});
                } else if (++this.n > 6) {
                    this.k(new Object[]{"Couldn't scoop the water.", var8});
                    this.n(new Object[]{var20});
                }
        }
    }

    private boolean e(Object[] var1) {
        Vec3d var4 = (Vec3d)var1[0];
        long var2 = (Long)var1[1];
        var2 = 90137803451192L ^ var2;
        long var5 = var2 ^ 116578936745903L;
        long var7 = var2 ^ 28557633491844L;
        RenderBatch var9 = WorldUtil.f_RenderBatch(new Object[]{b.player, var4, var5});
        float var10 = b.player.getYaw();
        float var11 = b.player.getPitch();
        b.player.setYaw(var9.a(new Object[0]));
        b.player.setPitch(var9.b(new Object[0]));

        boolean var12;
        try {
            var12 = InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var7});
        } finally {
            b.player.setYaw(var10);
            b.player.setPitch(var11);
        }

        return var12;
    }

    @Override
    public RenderBatch a(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 29247161167908L;
        long var6 = var2 ^ 106952455144116L;
        long var8 = var2 ^ 33065978990036L;
        long var10 = var2 ^ 108292967674203L;
        long var12 = var2 ^ 101816702374277L;
        long var14 = var2 ^ 136752692109656L;
        long var16 = var2 ^ 133051409312800L;
        long var18 = var2 ^ 13258986938553L;
        long var20 = var2 ^ 36186424613721L;
        if (!((String)this.a.getValue()).equalsIgnoreCase("Blatant")) {
            return null;
        } else if (b.player == null || b.world == null) {
            return null;
        } else if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem()) {
            if (Prestige.o.d_boolean______(new Object[0])) {
                return null;
            } else {
                ScoopState var23 = this.i;
                if (var23 != ScoopState.PLACE && var23 != ScoopState.SCOOP) {
                    return null;
                } else if (!this.o.b(new Object[]{this.c, var8})) {
                    return null;
                } else {
                    Vec3d var24 = this.a_class_243___(new Object[]{var23 == ScoopState.PLACE ? this.j : this.j.up(), var10});
                    RenderBatch var25 = Prestige.o.a_RenderBatch(new Object[]{var24, var12});
                    if (var25.a_boolean________________________________________(new Object[0])) {
                        return var25;
                    } else {
                        Prestige.o.a(new Object[]{var25});
                        if (var23 == ScoopState.PLACE) {
                            if (!this.g(new Object[]{Items.WATER_BUCKET, var16})) {
                                return null;
                            }

                            if (this.f(new Object[]{var25, var18})) {
                                this.i = ScoopState.WAIT;
                                this.m = 0;
                                this.o.a_void_____________________(new Object[]{var4});
                                this.c.reroll(new Object[]{var14});
                            }
                        } else {
                            if (!this.g(new Object[]{Items.BUCKET, var16})) {
                                return null;
                            }

                            if (!b.world.getFluidState(this.j.up()).isStill()) {
                                this.n(new Object[]{var20});
                                return null;
                            }

                            if (this.f(new Object[]{var25, var18})) {
                                this.n(new Object[]{var20});
                            } else if (++this.n > 6) {
                                this.k(new Object[]{"Couldn't scoop the water.", var6});
                                this.n(new Object[]{var20});
                            }
                        }

                        return var25;
                    }
                }
            }
        } else {
            return null;
        }
    }

    private boolean f(Object[] var1) {
        RenderBatch var4 = (RenderBatch)var1[0];
        long var2 = (Long)var1[1];
        var2 = 90137803451192L ^ var2;
        long var5 = var2 ^ 539515896602L;
        float var7 = b.player.getYaw();
        float var8 = b.player.getPitch();
        b.player.setYaw(var4.a(new Object[0]));
        b.player.setPitch(var4.b(new Object[0]));

        boolean var9;
        try {
            var9 = InventoryUtil2.c(new Object[]{Hand.MAIN_HAND, var5});
        } finally {
            b.player.setYaw(var7);
            b.player.setPitch(var8);
        }

        return var9;
    }

    private boolean g(Object[] var1) {
        Item var4 = (Item)var1[0];
        long var2 = (Long)var1[1];
        var2 = 90137803451192L ^ var2;
        long var5 = var2 ^ 103348834933175L;
        long var7 = var2 ^ 32282188500741L;
        long var9 = var2 ^ 102158020733695L;
        long var11 = var2 ^ 33373146405913L;
        long var13 = var2 ^ 93539481725278L;
        if (InventoryUtil.l(new Object[]{var4, var9})) {
            return true;
        } else if (!((String)this.d.getValue()).equals("Normal")) {
            return false;
        } else if (InventoryUtil.a_Integer(new Object[]{var4, var5}) == null) {
            this.o(new Object[]{var11});
            return false;
        } else {
            if (this.l == -1) {
                this.l = InventoryUtil.a_int_____(new Object[]{var7});
            }

            InventoryUtil.h(new Object[]{var4, var13});
            return false;
        }
    }

    private void n(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 90137803451192L ^ var2;
        long var4 = var2 ^ 118241449705541L;
        long var6 = var2 ^ 14790502109497L;
        this.i = ScoopState.BACK;
        this.o.a_void_____________________(new Object[]{var4});
        this.c.reroll(new Object[]{var6});
    }

    private void o(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 90137803451192L ^ var2;
        long var4 = var2 ^ 11890637555191L;
        long var6 = var2 ^ 63286334814680L;
        if ((Boolean)this.e.getValue() && this.l != -1 && !Prestige.o.d_boolean______(new Object[0])) {
            int var10000 = this.l;
            Object[] var10003 = new Object[]{null, var4};
            var10003[0] = var10000;
            InventoryUtil.a_void________(var10003);
            this.l = -1;
        }

        this.j(new Object[]{var6});
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
