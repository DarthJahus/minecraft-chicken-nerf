package net.sssubtlety.chicken_nerf;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.sssubtlety.chicken_nerf.ChickenNerf.NAMESPACE;
import static net.sssubtlety.chicken_nerf.FeatureControl.isConfigLoaded;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    private static final String NO_CONFIG_KEY_PREFIX = "text." + NAMESPACE + ".no_config_screen.";

    public static final Text NO_CONFIG_SCREEN_TITLE = Text.translatable(NO_CONFIG_KEY_PREFIX + "title");
    public static final Text NO_CONFIG_SCREEN_MESSAGE = Text.translatable(NO_CONFIG_KEY_PREFIX + "message");

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return isConfigLoaded() ?
            parent -> AutoConfig.getConfigScreen(Config.class, parent).get() :
            NoConfigScreen::new;
    }

    public static class NoConfigScreen extends Screen {
        // WHITE's color is non-null
        @SuppressWarnings("DataFlowIssue")
        private static final int TITLE_COLOR = Formatting.WHITE.getColorValue();
        // RED's color is non-null
        @SuppressWarnings("DataFlowIssue")
        private static final int MESSAGE_COLOR = Formatting.RED.getColorValue();

        private final Screen parent;
        protected NoConfigScreen(Screen parent) {
            super(NO_CONFIG_SCREEN_TITLE);
            this.parent = parent;
        }

        @Override
        public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
            super.render(graphics, mouseX, mouseY, delta);
            final int windowHCenter = MinecraftClient.getInstance().getWindow().getScaledWidth() / 2;
            final int windowHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();
            graphics.drawCenteredShadowedText(
                MinecraftClient.getInstance().textRenderer, NO_CONFIG_SCREEN_TITLE,
                windowHCenter, windowHeight / 10,
                TITLE_COLOR
            );
            graphics.drawCenteredShadowedText(
                MinecraftClient.getInstance().textRenderer, NO_CONFIG_SCREEN_MESSAGE,
                windowHCenter, windowHeight / 2,
                MESSAGE_COLOR
            );
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        public void closeScreen() {
            this.client.setScreen(this.parent);
        }
    }
}
