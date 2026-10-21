package dev.zprestige.prestige.hud;

import dev.zprestige.prestige.BoolSetting;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.gui.HudEditorScreen;
import dev.zprestige.prestige.math.MathHelper4;
import dev.zprestige.prestige.render.Font;
import dev.zprestige.prestige.render.MatrixStack;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderHelper19;
import dev.zprestige.prestige.render.RenderHelper21;
import dev.zprestige.prestige.render.RenderState;
import dev.zprestige.prestige.type.SettingFlag;
import dev.zprestige.prestige.type.TrajectoryType;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

public class HudElement implements RenderContract2 {
    private final String a;
    private final String c;
    private final BoolSetting d;
    public float e;
    public float f;
    public float g;
    public float h;
    public SettingFlag i;
    public SettingFlag j;
    public float k;
    public float l;
    private static final float m = 8.0F;
    private boolean n;
    private float o;
    private float p;
    private boolean q;
    private boolean r;
    private boolean s;
    private float t;
    private float u;
    private static final String A = "Enabled";

    public HudElement(String var1, String var2, long var3) {
        this.d = new BoolSetting(A, false);
        this.e = 0.0F;
        this.f = 0.0F;
        this.g = 0.0F;
        this.h = 0.0F;
        this.i = SettingFlag.Start;
        this.j = SettingFlag.Start;
        this.k = 0.0F;
        this.l = 0.0F;
        this.n = false;
        this.o = 0.0F;
        this.p = 0.0F;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = 0.0F;
        this.u = 0.0F;
        this.a = var1;
        this.c = var2;
    }

    public void a_void_______(Object[] var1) {
        float var2 = (Float)var1[0];
        float var5 = (Float)var1[1];
        long var3 = (Long)var1[2];
        var3 = 108265149825913L ^ var3;
        long var6 = var3 ^ 80516450163907L;
        this.e = var2;
        this.f = var5;
        this.b_void_(new Object[]{var6});
    }

    public boolean a_boolean_____(Object[] var1) {
        return this.s;
    }

    public void b_void_(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 108265149825913L ^ var2;
        long var4 = var2 ^ 76320656023544L;
        long var6 = var2 ^ 19124974948314L;
        long var8 = var2 ^ 3254206067272L;
        if (b.getWindow() != null) {
            if (!(this.g <= 0.0F) && !(this.h <= 0.0F)) {
                this.n = false;
                float var10001 = b.getWindow().getFramebufferWidth() / HudEditorScreen.b(new Object[]{var4});
                Object[] var10004 = new Object[]{null, var6};
                var10004[0] = var10001;
                this.c(var10004);
                var10001 = b.getWindow().getFramebufferHeight() / HudEditorScreen.b(new Object[]{var4});
                var10004 = new Object[]{null, var8};
                var10004[0] = var10001;
                this.d(var10004);
            } else {
                this.o = this.e;
                this.p = this.f;
                this.n = true;
            }
        }
    }

    private void c(Object[] var1) {
        float var4 = (Float)var1[0];
        Event.b();
        float var6 = this.e + this.g / 2.0F - var4 / 2.0F;
        if (Math.abs(var6) <= 8.0F) {
            this.i = SettingFlag.Center;
            this.k = var6;
        } else if (var6 < 0.0F) {
            this.i = SettingFlag.Start;
            this.k = this.e;
        } else {
            this.i = SettingFlag.End;
            this.k = var4 - (this.e + this.g);
        }
    }

    private void d(Object[] var1) {
        float var2 = (Float)var1[0];
        Event.b();
        float var6 = this.f + this.h / 2.0F - var2 / 2.0F;
        if (Math.abs(var6) <= 8.0F) {
            this.j = SettingFlag.Center;
            this.l = var6;
        } else if (var6 < 0.0F) {
            this.j = SettingFlag.Start;
            this.l = this.f;
        } else {
            this.j = SettingFlag.End;
            this.l = var2 - (this.f + this.h);
        }
    }

    public boolean b_boolean____(Object[] var1) {
        return this.n;
    }

    public boolean c_boolean__(Object[] var1) {
        return this.i == SettingFlag.End;
    }

    public boolean d_boolean(Object[] var1) {
        return this.j == SettingFlag.End;
    }

    public void e_void(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 108265149825913L ^ var2;
        long var4 = var2 ^ 112449576529060L;
        long var6 = var2 ^ 71859320860709L;
        if (b.getWindow() != null) {
            float var9 = b.getWindow().getFramebufferWidth() / HudEditorScreen.b(new Object[]{var4});
            float var10 = b.getWindow().getFramebufferHeight() / HudEditorScreen.b(new Object[]{var4});
            if (this.n) {
                if (!(this.g > 0.0F) || !(this.h > 0.0F)) {
                    this.e = Math.clamp(this.o, 0.0F, Math.max(0.0F, var9 - this.g));
                    this.f = Math.clamp(this.p, 0.0F, Math.max(0.0F, var10 - this.h));
                    return;
                }

                this.e = this.o;
                this.f = this.p;
                this.b_void_(new Object[]{var6});
            }
            float var11 = switch (this.i) {
                case Start -> this.k;
                case Center -> var9 / 2.0F - this.g / 2.0F + this.k;
                case End -> var9 - this.k - this.g;
            };

            float var12 = switch (this.j) {
                case Start -> this.l;
                case Center -> var10 / 2.0F - this.h / 2.0F + this.l;
                case End -> var10 - this.l - this.h;
            };
            this.e = Math.clamp(var11, 0.0F, Math.max(0.0F, var9 - this.g));
            this.f = Math.clamp(var12, 0.0F, Math.max(0.0F, var10 - this.h));
            this.q = this.e != var11;
            this.r = this.f != var12;
        }
    }

    public void f_void(Object[] var1) {
        SettingFlag var7 = (SettingFlag)var1[0];
        SettingFlag var6 = (SettingFlag)var1[1];
        float var2 = (Float)var1[2];
        float var3 = (Float)var1[3];
        long var4 = (Long)var1[4];
        var4 = 108265149825913L ^ var4;
        long var8 = var4 ^ 102782441257463L;
        this.i = var7;
        this.j = var6;
        this.k = var2;
        this.l = var3;
        this.n = false;
        this.e_void(new Object[]{var8});
    }

    public void g(Object[] var1) {
        TrajectoryType var2 = (TrajectoryType)var1[0];
        float var4 = (Float)var1[1];
        float var3 = (Float)var1[2];
        long var5 = (Long)var1[3];
        var5 = 108265149825913L ^ var5;
        long var7 = var5 ^ 111696313189519L;
        boolean var10 = var2 == TrajectoryType.TopLeft || var2 == TrajectoryType.BottomLeft;
        boolean var11 = var2 == TrajectoryType.TopLeft || var2 == TrajectoryType.TopRight;
        SettingFlag var10001 = var10 ? SettingFlag.Start : SettingFlag.End;
        SettingFlag var10002 = var11 ? SettingFlag.Start : SettingFlag.End;
        Object[] var10007 = new Object[]{null, null, null, null, var7};
        var10007[3] = var3;
        var10007[2] = var4;
        var10007[1] = var10002;
        var10007[0] = var10001;
        this.f_void(var10007);
    }

    public void h(Object[] var1) {
        float var2 = (Float)var1[0];
        float var5 = (Float)var1[1];
        long var3 = (Long)var1[2];
        var3 = 108265149825913L ^ var3;
        long var6 = var3 ^ 108113242399259L;
        long var8 = var3 ^ 112591891647942L;
        long var10 = var3 ^ 59756880949817L;
        long var12 = var3 ^ 41542923587499L;
        if (this.n) {
            this.o += var2;
            this.p += var5;
            this.e += var2;
            this.f += var5;
        } else {
            this.k = this.k + (this.i == SettingFlag.End ? -var2 : var2);
            this.l = this.l + (this.j == SettingFlag.End ? -var5 : var5);
            this.e_void(new Object[]{var8});
            if (b.getWindow() != null && !(this.g <= 0.0F) && !(this.h <= 0.0F)) {
                if (!this.q) {
                    float var10001 = b.getWindow().getFramebufferWidth() / HudEditorScreen.b(new Object[]{var6});
                    Object[] var10004 = new Object[]{null, var10};
                    var10004[0] = var10001;
                    this.c(var10004);
                }

                if (!this.r) {
                    float var15 = b.getWindow().getFramebufferHeight() / HudEditorScreen.b(new Object[]{var6});
                    Object[] var16 = new Object[]{null, var12};
                    var16[0] = var15;
                    this.d(var16);
                }
            }
        }
    }

    public void i(Object[] var1) {
        float var3 = (Float)var1[0];
        float var2 = (Float)var1[1];
        this.g = var3;
        this.h = var2;
    }

    public void j(Object[] var1) {
        long var2 = (Long)var1[0];
        long var4 = var2 ^ 111822382315182L;
        long var6 = var2 ^ 98101078674063L;
        long var8 = var2 ^ 55198602109446L;
        this.d.setValue(!(Boolean)this.d.getValue());
        if (this.e_boolean(new Object[]{var8})) {
            Prestige.eventBus.register(new Object[]{this, var6});
        } else {
            Prestige.eventBus.unregister(new Object[]{this, var4});
        }
    }

    public boolean e_boolean(Object[] var1) {
        return (Boolean)this.d.getValue();
    }

    public void k(Object[] var1) {
    }

    public void l(Object[] var1) {
    }

    public void m(Object[] var1) {
    }

    public void n(Object[] var1) {
        Matrix4f var5 = (Matrix4f)var1[0];
        float var2 = (Float)var1[1];
        float var4 = (Float)var1[2];
        float var8 = (Float)var1[3];
        float var9 = (Float)var1[4];
        int var6 = (Integer)var1[5];
        int var7 = (Integer)var1[6];
        int var3 = (Integer)var1[7];
        ArrayList var10 = (ArrayList)var1[8];
        long var11 = (Long)var1[9];
        var11 = 108265149825913L ^ var11;
        long var13 = var11 ^ 109773111413292L;
        long var15 = var11 ^ 94645669324319L;
        long var17 = var11 ^ 37312740351629L;
        long var19 = var11 ^ 62262237669224L;
        long var21 = var11 ^ 6904928358816L;
        long var23 = var11 ^ 57673909236646L;
        long var25 = var11 ^ 40029431347208L;
        long var27 = var11 ^ 139062542428127L;
        long var29 = var11 ^ 27004907234505L;
        long var31 = var11 ^ 111752008167369L;
        boolean var34 = var6 > var2 && var6 < var2 + var8 && var7 > var4 && var7 < var4 + var9;
        if (this.s) {
            float var35 = var6 + this.t;
            float var36 = var7 + this.u;
            this.e = var35;
            this.f = var36;
            if (GLFW.glfwGetKey(b.getWindow().getHandle(), 340) != 1) {
                float var37 = 5.0F;
                float var38 = 5.0F;
                RenderHelper21 var39 = null;
                RenderHelper21 var40 = null;
                byte var41 = 0;
                byte var42 = 0;

                for (RenderHelper21 var44 : (Iterable<RenderHelper21>)(var10)) {
                    switch (RenderHelper19.a[var44.a(new Object[0]).ordinal()]) {
                        case 1:
                            float var45 = Math.abs(var44.a_int_______________________(new Object[0]) - this.e);
                            float var46 = Math.abs(var44.a_int_______________________(new Object[0]) - (this.e + this.g));
                            float var47 = Math.abs(var44.a_int_______________________(new Object[0]) - (this.e + this.g / 2.0F));
                            if (var45 < var37) {
                                var37 = var45;
                                var39 = var44;
                                var41 = 0;
                            }

                            if (var46 < var37) {
                                var37 = var46;
                                var39 = var44;
                                var41 = 1;
                            }

                            if (var47 < var37) {
                                var37 = var47;
                                var39 = var44;
                                var41 = 2;
                            }
                            break;
                        case 2:
                            float var48 = Math.abs(var44.a_int_______________________(new Object[0]) - this.f);
                            float var49 = Math.abs(var44.a_int_______________________(new Object[0]) - (this.f + this.h));
                            float var50 = Math.abs(var44.a_int_______________________(new Object[0]) - (this.f + this.h / 2.0F));
                            if (var48 < var38) {
                                var38 = var48;
                                var40 = var44;
                                var42 = 0;
                            }

                            if (var49 < var38) {
                                var38 = var49;
                                var40 = var44;
                                var42 = 1;
                            }

                            if (var50 < var38) {
                                var38 = var50;
                                var40 = var44;
                                var42 = 2;
                            }
                    }
                }

                float var56 = b.getWindow().getFramebufferWidth() / HudEditorScreen.b(new Object[]{var13});
                float var57 = b.getWindow().getFramebufferHeight() / HudEditorScreen.b(new Object[]{var13});
                if (var39 != null) {
                    this.e = var41 == 0
                        ? var39.a_int_______________________(new Object[0])
                        : (
                            var41 == 1
                                ? var39.a_int_______________________(new Object[0]) - this.g
                                : var39.a_int_______________________(new Object[0]) - this.g / 2.0F
                        );
                    float var10001 = var39.a_int_______________________(new Object[0]) - 0.5F;
                    float var10003 = var39.a_int_______________________(new Object[0]) + 0.5F;
                    Object[] var10008 = new Object[]{null, null, null, null, null, MathHelper4.d(new Object[]{var15}), var31};
                    var10008[4] = var57;
                    var10008[3] = var10003;
                    var10008[2] = -1.0F;
                    var10008[1] = var10001;
                    var10008[0] = var5;
                    MatrixStack.r(var10008);
                }

                if (var40 != null) {
                    this.f = var42 == 0
                        ? var40.a_int_______________________(new Object[0])
                        : (
                            var42 == 1
                                ? var40.a_int_______________________(new Object[0]) - this.h
                                : var40.a_int_______________________(new Object[0]) - this.h / 2.0F
                        );
                    float var10002 = var40.a_int_______________________(new Object[0]) - 0.5F;
                    float var10004 = var40.a_int_______________________(new Object[0]) + 0.5F;
                    Object[] var66 = new Object[]{null, null, null, null, null, MathHelper4.d(new Object[]{var15}), var31};
                    var66[4] = var10004;
                    var66[3] = var56;
                    var66[2] = var10002;
                    var66[1] = 0.0F;
                    var66[0] = var5;
                    MatrixStack.r(var66);
                }
            }
        }

        Color var52 = this.e_boolean(new Object[]{var23})
            ? new Color(
                MathHelper4.d(new Object[]{var15}).getRed(), MathHelper4.d(new Object[]{var15}).getGreen(), MathHelper4.d(new Object[]{var15}).getBlue(), 40
            )
            : new Color(255, 255, 255, var34 ? 12 : 5);
        float var60 = var2 + var8;
        float var63 = var4 + var9;
        Object[] var10009 = new Object[]{null, null, null, null, null, var52, new Vector4f(3.0F), var25};
        var10009[4] = var63;
        var10009[3] = var60;
        var10009[2] = var4;
        var10009[1] = var2;
        var10009[0] = var5;
        RenderState.u(var10009);
        if (this.e_boolean(new Object[]{var23})) {
            float var58 = var4 + 1.5F;
            var60 = var2 + 2.0F;
            var63 = var4 + var9 - 1.5F;
            var10009 = new Object[]{null, null, null, null, null, MathHelper4.d(new Object[]{var15}), new Vector4f(1.0F), var25};
            var10009[4] = var63;
            var10009[3] = var60;
            var10009[2] = var58;
            var10009[1] = var2;
            var10009[0] = var5;
            RenderState.u(var10009);
        }

        var60 = var2 + var8;
        var63 = var4 + var9;
        Color var10005 = new Color(255, 255, 255, var34 ? 18 : 8);
        var10009 = new Object[]{null, null, null, null, null, null, null, var17};
        var10009[6] = 3.0F;
        var10009[5] = var10005;
        var10009[4] = var63;
        var10009[3] = var60;
        var10009[2] = var4;
        var10009[1] = var2;
        var10009[0] = var5;
        MatrixStack.l(var10009);
        float var53 = var2 + (this.e_boolean(new Object[]{var23}) ? 6.0F : 3.0F);
        float var54 = var4 + var9 / 2.0F - Prestige.c.a(new Object[]{var19}).c_float___(new Object[]{var27}) / 2.0F;
        Color var55 = this.e_boolean(new Object[]{var23}) ? Color.WHITE : new Color(180, 180, 180);
        Font var10000 = Prestige.c.a(new Object[]{var19});
        String var59 = this.a;
        Object[] var67 = new Object[]{null, null, null, null, var55, var29};
        var67[3] = var54;
        var67[2] = var53;
        var67[1] = var59;
        var67[0] = var5;
        var10000.v(var67);
        if (var3 == 1 && var34) {
            this.j(new Object[]{var21});
        }
    }

    public void o(Object[] var1) {
        int var4 = (Integer)var1[0];
        int var6 = (Integer)var1[1];
        boolean var5 = (Boolean)var1[2];
        long var2 = (Long)var1[3];
        var2 = 108265149825913L ^ var2;
        long var7 = var2 ^ 60207457423565L;
        if (var5) {
            if (this.s) {
                this.s = false;
                this.b_void_(new Object[]{var7});
            }
        } else {
            boolean var10 = var4 > this.e && var4 < this.e + this.g && var6 > this.f && var6 < this.f + this.h;
            if (var10) {
                this.t = this.e - var4;
                this.u = this.f - var6;
                this.s = true;
            }
        }
    }

    public void q(Object[] var1) {
        Matrix4f var2 = (Matrix4f)var1[0];
        float var10003 = this.e + this.g;
        float var10004 = this.f + this.h;
        long var5 = (Long)var1[1] ^ 46774337756829L;
        MatrixStack.l(new Object[]{var2, this.e, this.f, var10003, var10004, Color.WHITE, 0.0F, var5});
    }

    public String a_String____(Object[] var1) {
        return this.a;
    }

    public float a_float_______(Object[] var1) {
        return this.e;
    }

    public float b_float___(Object[] var1) {
        return this.f;
    }

    private static Field c(Class var0, String var1, Class var2) {
        for (Field var6 : var0.getDeclaredFields()) {
            if (var6.getName().equals(var1) && var6.getType() == var2) {
                return var6;
            }
        }

        return null;
    }

    private static Field d(Class var0, String var1, Class var2) {
        Field var3 = c(var0, var1, var2);
        if (var3 != null) {
            return var3;
        } else {
            Class[] var4 = var0.getInterfaces();
            if (var4 != null) {
                for (int var5 = 0; var5 < var4.length; var5++) {
                    var3 = d(var4[var5], var1, var2);
                    if (var3 != null) {
                        return var3;
                    }
                }
            }

            return null;
        }
    }

    private static Method c(Class var0, String var1, Class var2, int var3, Class[] var4) {
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

    private static Method d(Class var0, String var1, Class var2, int var3, Class[] var4) {
        Method var5 = c(var0, var1, var2, var3, var4);
        if (var5 != null) {
            return var5;
        } else {
            Class[] var6 = var0.getInterfaces();
            if (var6 != null) {
                for (int var7 = 0; var7 < var6.length; var7++) {
                    var5 = d(var6[var7], var1, var2, var3, var4);
                    if (var5 != null) {
                        return var5;
                    }
                }
            }

            return null;
        }
    }

    private static MatchException b(MatchException var0) {
        return var0;
    }
}
