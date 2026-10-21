package dev.zprestige.prestige.module.combat;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.EnumSetting;
import dev.zprestige.prestige.Gamemode;
import dev.zprestige.prestige.IntSetting;
import dev.zprestige.prestige.KeybindSetting;
import dev.zprestige.prestige.Module;
import dev.zprestige.prestige.RangeSetting;
import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.PacketEvent;
import dev.zprestige.prestige.event.PacketEvent2;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.hud.ModuleNotificationsElement;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.inventory.InventoryUtil2;
import dev.zprestige.prestige.render.RenderHelper27;
import dev.zprestige.prestige.type.NotificationType;
import dev.zprestige.prestige.world.WorldUtil2;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class AnchorMacro extends Module {
    private BoolSetting d;
    private RangeSetting a;
    private BoolSetting c;
    private KeybindSetting f;
    private EnumSetting e;
    private BoolSetting g;
    private BoolSetting h;
    private EnumSetting i;
    private BoolSetting j;
    private BoolSetting k;
    private BoolSetting l;
    private EnumSetting m;
    private IntSetting n;
    private BoolSetting o;
    public static boolean p;
    private RenderHelper27 q = new RenderHelper27(26293358099357L);
    private boolean r;
    private ArrayList s = new ArrayList();
    private ArrayList t = new ArrayList();
    private int u = -1;
    private int v = -1;
    private int w = -1;

    public AnchorMacro() {
        dev.zprestige.prestige.ui.bridge.Bootstrap.pre(this);
        this.n.setVisibility_IntSetting(new Object[]{(Predicate<Integer>)lpL97_0 -> (Boolean)this.l.getValue(), 98265949679296L});
        this.o
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL96_0 -> (Boolean)this.l.getValue() && ((String)this.m.getValue()).equals("Swap"), 73196168581295L}
            );
        this.i.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL101_0 -> (Boolean)this.h.getValue(), 90319707064974L});
        this.k.setVisibility_BoolSetting(new Object[]{(Predicate<Boolean>)lpL99_0 -> (Boolean)this.h.getValue(), 73196168581295L});
        this.j
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL100_0 -> (Boolean)this.h.getValue() && ((String)this.i.getValue()).equals("Swap"), 73196168581295L}
            );
        this.m.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL98_0 -> (Boolean)this.l.getValue(), 90319707064974L});
        this.f.setVisibility_KeybindSetting(new Object[]{(Predicate<Integer>)lpL104_0 -> (Boolean)this.c.getValue(), 112715924896781L});
        this.g
            .setVisibility_BoolSetting(
                new Object[]{(Predicate<Boolean>)lpL102_0 -> (Boolean)this.c.getValue() && ((String)this.e.getValue()).equals("Swap"), 73196168581295L}
            );
        this.e.setVisibility_EnumSetting(new Object[]{(Predicate<String>)lpL103_0 -> (Boolean)this.c.getValue(), 90319707064974L});
    }

    @Override
    public Set getGamemodes(Object[] var1) {
        return Set.of(Gamemode.Crystal);
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        this.s.clear();
        this.t.clear();
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (b.currentScreen == null && b.isWindowFocused() && !b.player.isUsingItem()) {
            if (!AutoObbyCrystal.R && !AnchorPearlCatch.C && !Prestige.o.d_boolean______(new Object[0])) {
                if (this.q.b(new Object[]{this.a, 89714527855213L})) {
                    if ((Boolean)this.d.getValue()) {
                        this.s.removeIf(lpL95_0 -> b.world.getBlockState(((net.minecraft.util.math.BlockPos)lpL95_0)).getBlock() != Blocks.RESPAWN_ANCHOR);
                    }

                    this.q.a_void_____________________(new Object[]{93540861878173L});
                    this.a.reroll(new Object[]{57083216809697L});
                    p = true;
                    if (!(Boolean)this.c.getValue() || !this.d(new Object[]{34842388242754L})) {
                        if (!(Boolean)this.h.getValue() || !this.e(new Object[]{94313242050293L})) {
                            if ((Boolean)this.l.getValue()) {
                                this.j(new Object[]{32886828184658L});
                            }

                            p = false;
                        }
                    }
                }
            }
        }
    }

    private boolean d(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 65616062650638L ^ var2;
        long var4 = var2 ^ 71530962494024L;
        long var6 = var2 ^ 127361578402766L;
        long var8 = var2 ^ 116010806500946L;
        long var10 = var2 ^ 117078214650755L;
        long var12 = var2 ^ 6731204092475L;
        long var14 = var2 ^ 131087805649937L;
        long var16 = var2 ^ 51876427273586L;
        long var18 = var2 ^ 103575000047592L;
        long var20 = var2 ^ 68336797087553L;
        long var22 = var2 ^ 34663137311250L;
        long var24 = var2 ^ 64291855462668L;
        if ((Integer)this.f.getValue() == -1) {
            return false;
        } else if (this.u != -1) {
            int var10000 = this.u;
            Object[] var34 = new Object[]{null, var12};
            var34[0] = var10000;
            InventoryUtil.a_void________(var34);
            this.u = -1;
            return false;
        } else if (!this.f.isPressed(new Object[]{var20})) {
            this.r = false;
            return false;
        } else {
            if (!this.r) {
                ModuleNotificationsElement.s(new Object[]{this, "Is placing an anchor.", NotificationType.INFO, var4});
            }

            if (!this.r && b.crosshairTarget instanceof BlockHitResult var27) {
                boolean var33 = WorldUtil2.b(new Object[]{var27.getBlockPos(), Blocks.RESPAWN_ANCHOR, var14})
                    && WorldUtil2.a_int______________________________(new Object[]{var27.getBlockPos(), var10}) == 0;
                if (!WorldUtil2.d(new Object[]{var27, var8}) && !var33) {
                    return false;
                } else {
                    int var29 = -1;
                    String var30 = (String)this.e.getValue();
                    switch (var30) {
                        case "None":
                            if (var33 && !InventoryUtil.l(new Object[]{Items.GLOWSTONE, var22})) {
                                return false;
                            }

                            if (!InventoryUtil.l(new Object[]{Items.RESPAWN_ANCHOR, var22})) {
                                return false;
                            }
                            break;
                        case "Swap":
                            if ((Boolean)this.g.getValue()) {
                                this.u = InventoryUtil.a_int_____(new Object[]{var18});
                            }

                            if (var33) {
                                if (!InventoryUtil.i(new Object[]{Blocks.GLOWSTONE, var24})) {
                                    return false;
                                }
                            } else if (!InventoryUtil.i(new Object[]{Blocks.RESPAWN_ANCHOR, var24})) {
                                return false;
                            }
                            break;
                        case "Silent":
                            var29 = InventoryUtil.a_int_____(new Object[]{var18});
                            if (var33) {
                                if (!InventoryUtil.i(new Object[]{Blocks.GLOWSTONE, var24})) {
                                    return false;
                                }
                            } else if (!InventoryUtil.i(new Object[]{Blocks.RESPAWN_ANCHOR, var24})) {
                                return false;
                            }
                    }

                    if (!var33) {
                        this.t
                            .add(
                                !WorldUtil2.b(new Object[]{var27.getBlockPos(), Blocks.FIRE, var14})
                                        && !WorldUtil2.b(new Object[]{var27.getBlockPos(), WorldUtil2.a_class_2248_(new Object[]{var16}), var14})
                                    ? var27.getBlockPos().add(var27.getSide().getOffsetX(), var27.getSide().getOffsetY(), var27.getSide().getOffsetZ())
                                    : var27.getBlockPos()
                            );
                    }

                    InventoryUtil2.a(new Object[]{var27, var6});
                    if (var29 != -1) {
                        Object[] var10003 = new Object[]{null, var12};
                        var10003[0] = var29;
                        InventoryUtil.a_void________(var10003);
                    }

                    this.r = true;
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public boolean e(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 65616062650638L ^ var2;
        long var4 = var2 ^ 63371416509561L;
        long var6 = var2 ^ 20598233555509L;
        long var8 = var2 ^ 35236012602420L;
        long var10 = var2 ^ 84011043126668L;
        long var12 = var2 ^ 22282499211359L;
        long var14 = var2 ^ 94496806856101L;
        long var16 = var2 ^ 123207551184571L;
        if (this.v != -1) {
            int var10000 = this.v;
            Object[] var25 = new Object[]{null, var10};
            var25[0] = var10000;
            InventoryUtil.a_void________(var25);
            this.v = -1;
            return false;
        } else if (!(b.crosshairTarget instanceof BlockHitResult var19)) {
            return false;
        } else if (b.player.isSneaking()
            || !WorldUtil2.k(new Object[]{var19.getBlockPos(), Blocks.RESPAWN_ANCHOR, var6})
            || (Boolean)this.d.getValue() && !this.s.contains(var19.getBlockPos())
            || WorldUtil2.a_int______________________________(new Object[]{var19.getBlockPos(), var8}) != 0) {
            return false;
        } else if ((Boolean)this.k.getValue() && !this.t.contains(var19.getBlockPos())) {
            return false;
        } else {
            int var24 = -1;
            String var21 = (String)this.i.getValue();
            switch (var21) {
                case "None":
                    if (!InventoryUtil.l(new Object[]{Items.GLOWSTONE, var14})) {
                        return false;
                    }
                    break;
                case "Swap":
                    if ((Boolean)this.j.getValue()) {
                        this.v = InventoryUtil.a_int_____(new Object[]{var12});
                    }

                    if (!InventoryUtil.i(new Object[]{Blocks.GLOWSTONE, var16})) {
                        return false;
                    }
                    break;
                case "Silent":
                    var24 = InventoryUtil.a_int_____(new Object[]{var12});
                    if (!InventoryUtil.i(new Object[]{Blocks.GLOWSTONE, var16})) {
                        return false;
                    }
            }

            InventoryUtil2.a(new Object[]{var19, var4});
            if (var24 != -1) {
                Object[] var10003 = new Object[]{null, var10};
                var10003[0] = var24;
                InventoryUtil.a_void________(var10003);
            }

            return true;
        }
    }

    private void j(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 65616062650638L ^ var2;
        long var4 = var2 ^ 124849367881438L;
        long var6 = var2 ^ 99599932516498L;
        long var8 = var2 ^ 114495422709395L;
        long var10 = var2 ^ 4700113860395L;
        long var12 = var2 ^ 101610345679608L;
        if (this.w != -1) {
            int var22 = this.w;
            Object[] var25 = new Object[]{null, var10};
            var25[0] = var22;
            InventoryUtil.a_void________(var25);
            this.w = -1;
        } else if (b.crosshairTarget instanceof BlockHitResult var15) {
            if (!b.player.isSneaking()
                && WorldUtil2.k(new Object[]{var15.getBlockPos(), Blocks.RESPAWN_ANCHOR, var6})
                && (!(Boolean)this.d.getValue() || this.s.contains(var15.getBlockPos()))
                && WorldUtil2.a_int______________________________(new Object[]{var15.getBlockPos(), var8}) != 0) {
                int var20 = -1;
                String var17 = (String)this.m.getValue();
                switch (var17) {
                    case "None":
                        if ((Integer)this.n.getValue() - 1 != InventoryUtil.a_int_____(new Object[]{var12})) {
                            return;
                        }
                        break;
                    case "Swap":
                        if ((Boolean)this.o.getValue()) {
                            this.w = InventoryUtil.a_int_____(new Object[]{var12});
                        }

                        int var21 = (Integer)this.n.getValue() - 1;
                        Object[] var23 = new Object[]{null, var10};
                        var23[0] = var21;
                        InventoryUtil.a_void________(var23);
                        break;
                    case "Silent":
                        var20 = InventoryUtil.a_int_____(new Object[]{var12});
                        int var10000 = (Integer)this.n.getValue() - 1;
                        Object[] var10003 = new Object[]{null, var10};
                        var10003[0] = var10000;
                        InventoryUtil.a_void________(var10003);
                }

                InventoryUtil2.a(new Object[]{var15, var4});
                if (var20 != -1) {
                    Object[] var24 = new Object[]{null, var10};
                    var24[0] = var20;
                    InventoryUtil.a_void________(var24);
                }
            }
        }
    }

    @Subscribe
    public void a(PacketEvent2 var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof PlayerInteractBlockC2SPacket var9) {
            ItemStack var13 = var9.getHand().equals(Hand.MAIN_HAND) ? b.player.getMainHandStack() : b.player.getOffHandStack();
            if (var13.getItem() == Items.RESPAWN_ANCHOR) {
                BlockHitResult var11 = var9.getBlockHitResult();
                BlockPos var12 = var11.getBlockPos().add(var11.getSide().getOffsetX(), var11.getSide().getOffsetY(), var11.getSide().getOffsetZ());
                if (!(Boolean)this.d.getValue() || this.s.contains(var12)) {
                    return;
                }

                this.s
                    .add(
                        !WorldUtil2.b(new Object[]{var11.getBlockPos(), Blocks.RESPAWN_ANCHOR, 91415361137757L})
                                && !WorldUtil2.b(new Object[]{var11.getBlockPos(), Blocks.FIRE, 91415361137757L})
                                && !WorldUtil2.b(new Object[]{var11.getBlockPos(), WorldUtil2.a_class_2248_(new Object[]{12315318725950L}), 91415361137757L})
                            ? var12
                            : var11.getBlockPos()
                    );
            }
        }
    }

    @Subscribe
    public void a(PacketEvent var1) {
        Event.b();
        if (var1.a(new Object[0]) instanceof BlockUpdateS2CPacket var5) {
            if (var5.getState() == null || var5.getState().getBlock() == Blocks.RESPAWN_ANCHOR) {
                return;
            }

            this.t.remove(var5.getPos());
            this.s.remove(var5.getPos());
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
