package dev.zprestige.prestige.network;

import dev.zprestige.prestige.Subscribe;
import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.event.Event;
import dev.zprestige.prestige.event.TickEvent19;
import dev.zprestige.prestige.event.TickEvent3;
import dev.zprestige.prestige.inventory.InventoryUtil;
import dev.zprestige.prestige.render.RenderContract2;
import dev.zprestige.prestige.render.RenderHelper15;
import dev.zprestige.prestige.type.PacketResult;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.PlayerInput;

public class PacketQueue implements RenderContract2 {
    public static boolean a = true;
    private static final int c = 5;
    private final ArrayDeque d;
    private final List e;
    private volatile boolean f;
    private volatile boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private int k;

    public PacketQueue(long var1) {
        super();
        var1 = 0L;
        var1 = 59328805024258L ^ var1;
        long var3 = var1 ^ 31199158213519L;
        this.d = new ArrayDeque();
        this.e = new ArrayList();
        Prestige.eventBus.register(new Object[]{this, var3});
    }

    public PacketResult a(Object[] var1) {
        int var2 = (Integer)var1[0];
        int var3 = (Integer)var1[1];
        long var4 = (Long)var1[2];
        var4 = 59328805024258L ^ var4;
        long var6 = var4 ^ 53959615707372L;
        Object[] var10007 = new Object[]{null, null, null, null, var6};
        var10007[2] = var3;
        var10007[1] = var2;
        var10007[0] = null;
        return this.d(var10007);
    }

    public PacketResult b(Object[] var1) {
        int var2 = (Integer)var1[0];
        int var3 = (Integer)var1[1];
        Runnable var4 = (Runnable)var1[2];
        long var5 = (Long)var1[3];
        var5 = 59328805024258L ^ var5;
        long var7 = var5 ^ 71317711165431L;
        Object[] var10007 = new Object[]{null, null, null, var4, var7};
        var10007[2] = var3;
        var10007[1] = var2;
        var10007[0] = null;
        return this.d(var10007);
    }

    public PacketResult c(Object[] var1) {
        Object var5 = var1[0];
        int var4 = (Integer)var1[1];
        int var6 = (Integer)var1[2];
        long var2 = (Long)var1[3];
        var2 = 59328805024258L ^ var2;
        long var7 = var2 ^ 103336935611973L;
        Object[] var10007 = new Object[]{null, null, null, null, var7};
        var10007[2] = var6;
        var10007[1] = var4;
        var10007[0] = var5;
        return this.d(var10007);
    }

    public PacketResult d(Object[] var1) {
        Object var5 = var1[0];
        int var7 = (Integer)var1[1];
        int var6 = (Integer)var1[2];
        Runnable var2 = (Runnable)var1[3];
        long var3 = (Long)var1[4];
        var3 = 59328805024258L ^ var3;
        long var8 = var3 ^ 28513772488895L;
        long var10 = var3 ^ 72919772776651L;
        if (Prestige.w || b.player == null || b.world == null || b.interactionManager == null) {
            return PacketResult.REJECTED;
        } else if (!a) {
            Object[] var17 = new Object[]{null, null, SlotActionType.SWAP, var10};
            var17[1] = var6;
            var17[0] = var7;
            InventoryUtil.e(var17);
            return PacketResult.IMMEDIATE;
        } else if (b.isOnThread() && this.d_boolean_____(new Object[]{var8})) {
            Object[] var10005 = new Object[]{null, null, SlotActionType.SWAP, var10};
            var10005[1] = var6;
            var10005[0] = var7;
            InventoryUtil.e(var10005);
            return PacketResult.IMMEDIATE;
        } else {
            synchronized (this) {
                this.d.add(new RenderHelper15(var5, var7, var6, var2));
                this.k = 0;
            }

            return PacketResult.QUEUED;
        }
    }

    public int a_int___________________(Object[] var1) {
        Object var2 = var1[0];
        if (var2 == null) {
            return 0;
        } else {
            synchronized (this) {
                int var7 = this.d.size();
                this.d.removeIf(lpL23_0 -> ((dev.zprestige.prestige.render.RenderHelper15)lpL23_0).a == var2);
                int var8 = var7 - this.d.size();
                if (var8 > 0 && this.d.isEmpty()) {
                    this.k = 0;
                    if (this.i && this.h) {
                        this.j = true;
                    }
                }

                return var8;
            }
        }
    }

    // $VF: Could not create synchronized statement, marking monitor enters and exits
    // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
    public boolean a_boolean____________________________________(Object[] var1) {
        Event.b();
        PacketQueue var5 = this;
        synchronized (this){} // $VF: monitorenter 

        try {
            boolean var10000 = !this.d.isEmpty();
            // $VF: monitorexit
            return var10000;
        } finally {
            // $VF: monitorexit
        }
    }

    public boolean b_boolean______(Object[] var1) {
        if (!Prestige.w && a) {
            boolean var5;
            synchronized (this) {
                var5 = !this.d.isEmpty();
            }

            if (var5) {
                if (!this.i && b.player != null) {
                    this.h = b.player.isSprinting();
                }

                this.i = true;
            } else if (this.i) {
                this.i = false;
                this.h = false;
            }

            return var5;
        } else {
            return false;
        }
    }

    public boolean c_boolean___(Object[] var1) {
        if (!Prestige.w && this.j) {
            this.j = false;
            return true;
        } else {
            return false;
        }
    }

    public void a_void___________(Object[] var1) {
        Packet var4 = (Packet)var1[0];
        if (var4 instanceof ClientCommandC2SPacket var6) {
            if (var6.getMode() == Mode.START_SPRINTING) {
                this.f = true;
            } else if (var6.getMode() == Mode.STOP_SPRINTING) {
                this.f = false;
            }
        }

        if (var4 instanceof PlayerInputC2SPacket var8) {
            PlayerInput var7 = var8.input();
            this.g = var7.forward() || var7.backward() || var7.left() || var7.right() || var7.jump();
        }
    }

    @Subscribe
    public void a(TickEvent3 var1) {
        synchronized (this) {
            this.d.clear();
            this.k = 0;
        }

        this.e.clear();
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
    }

    @Subscribe
    public void a(TickEvent19 var1) {
        if (!Prestige.w) {
            if (!this.e.isEmpty()) {
                ArrayList var9 = new ArrayList(this.e);
                this.e.clear();

                for (Runnable var11 : (Iterable<Runnable>)(var9)) {
                    try {
                        var11.run();
                    } catch (Throwable var16) {
                    }
                }
            }

            boolean var18;
            synchronized (this) {
                var18 = !this.d.isEmpty();
                if (!var18) {
                    this.k = 0;
                }
            }

            if (var18 && b.player != null && b.world != null) {
                if (this.d_boolean_____(new Object[]{17526588285803L})) {
                    this.b_void____(new Object[]{102904732075667L});
                } else {
                    synchronized (this) {
                        this.k++;
                        if (this.k > 5) {
                            this.d.clear();
                            this.k = 0;
                        }
                    }
                }
            }
        }
    }

    // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
    // $VF: Could not create synchronized statement, marking monitor enters and exits
    // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
    private void b_void____(Object[] var1) {
        long var2 = (Long)var1[0];
        var2 = 59328805024258L ^ var2;
        long var4 = var2 ^ 66165550897038L;
        Event.b();
        PacketQueue var8 = this;
        synchronized (this){} // $VF: monitorenter 
        boolean var11 = false /* VF: Semaphore variable */;

        ArrayList var7;
        try {
            var11 = true;
            if (this.d.isEmpty()) {
                // $VF: monitorexit
                return;
            }

            var7 = new ArrayList(this.d);
            this.d.clear();
            this.k = 0;
            // $VF: monitorexit
            var11 = false;
        } finally {
            if (var11) {
                // $VF: monitorexit
            }
        }

        for (RenderHelper15 var9 : (Iterable<RenderHelper15>)(var7)) {
            int var10000 = var9.b;
            int var10001 = var9.c;
            Object[] var10005 = new Object[]{null, null, SlotActionType.SWAP, var4};
            var10005[1] = var10001;
            var10005[0] = var10000;
            InventoryUtil.e(var10005);
            if (var9.d != null) {
                this.e.add(var9.d);
            }
        }

        if (this.h) {
            this.j = true;
        }
    }

    private boolean d_boolean_____(Object[] var1) {
        return !this.f && !this.g;
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

    private static Throwable a(Throwable var0) {
        return var0;
    }
}
