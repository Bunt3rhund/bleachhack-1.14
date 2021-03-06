/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDrinker420/bleachhack-1.14/).
 * Copyright (c) 2019 Bleach.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bleach.hack.mixin;

import bleach.hack.module.ModuleManager;
import bleach.hack.module.mods.Nuker;
import bleach.hack.module.mods.SpeedMine;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientPlayerInteractionManager {
	@Shadow
	private int field_3716;

	@Redirect(method = "method_2902", at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;field_3716:I", ordinal = 3))
	public void onDamageBlockFirst(ClientPlayerInteractionManager clientPlayerInteractionManager, int i) {
		i = ModuleManager.getModule(Nuker.class).isToggled() ?
				(int) ModuleManager.getModule(Nuker.class).getSetting(2).asSlider().getValue()
				: ModuleManager.getModule(SpeedMine.class).isToggled()
				&& ModuleManager.getModule(SpeedMine.class).getSetting(0).asMode().mode == 1
				? (int) ModuleManager.getModule(SpeedMine.class).getSetting(2).asSlider().getValue() : 5;
				this.field_3716 = i;
	}

	@Redirect(method = "method_2902", at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;field_3716:I", ordinal = 4))
	public void onDamageBlockSecond(ClientPlayerInteractionManager clientPlayerInteractionManager, int i) {
		i = ModuleManager.getModule(Nuker.class).isToggled()
				? (int) ModuleManager.getModule(Nuker.class).getSetting(2).asSlider().getValue()
						: ModuleManager.getModule(SpeedMine.class).isToggled()
						&& ModuleManager.getModule(SpeedMine.class).getSetting(0).asMode().mode == 1
						? (int) ModuleManager.getModule(SpeedMine.class).getSetting(2).asSlider().getValue() : 5;
						this.field_3716 = i;
	}

	@Redirect(method = "attackBlock", at = @At(value = "FIELD", target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;field_3716:I"))
	public void attackBlock(ClientPlayerInteractionManager clientPlayerInteractionManager, int i) {
		i = ModuleManager.getModule(Nuker.class).isToggled()
				? (int) ModuleManager.getModule(Nuker.class).getSetting(2).asSlider().getValue()
						: ModuleManager.getModule(SpeedMine.class).isToggled()
						&& ModuleManager.getModule(SpeedMine.class).getSetting(0).asMode().mode == 1
						? (int) ModuleManager.getModule(SpeedMine.class).getSetting(2).asSlider().getValue() : 5;
						this.field_3716 = i;
	}
}
