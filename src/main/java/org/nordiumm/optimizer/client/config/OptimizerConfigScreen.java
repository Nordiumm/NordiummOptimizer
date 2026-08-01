package org.nordiumm.optimizer.client.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.nordiumm.optimizer.common.config.ConfigManager;
import org.nordiumm.optimizer.common.config.OptimizerConfig;

public class OptimizerConfigScreen extends Screen {

    private Button enabledButton;
    private Button totalEntitiesButton;
    private Button itemFramesButton;
    private Button playersButton;
    private Button armorStandsButton;
    private Button otherButton;

    private Button saveButton;
    private Button discardButton;

    private final Screen parent;

    public OptimizerConfigScreen(Screen parent) {
        super(Component.literal("NordiummOptimizer Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {

        enabledButton = Button.builder(
                Component.literal("HUD Enabled: " + OptimizerConfig.enabled),
                button -> {
                    OptimizerConfig.enabled = !OptimizerConfig.enabled;
                    updateButtons();
                }
        ).bounds(10, 40, 200, 20).build();

        addRenderableWidget(enabledButton);


        totalEntitiesButton = Button.builder(
                Component.literal("Show Entities: " + OptimizerConfig.showTotalEntities),
                button -> {
                    OptimizerConfig.showTotalEntities = !OptimizerConfig.showTotalEntities;
                    updateButtons();
                }
        ).bounds(10, 70, 200, 20).build();

        addRenderableWidget(totalEntitiesButton);


        itemFramesButton = Button.builder(
                Component.literal("Show Item Frames: " + OptimizerConfig.showItemFrames),
                button -> {
                    OptimizerConfig.showItemFrames = !OptimizerConfig.showItemFrames;
                    updateButtons();
                }
        ).bounds(10, 100, 200, 20).build();

        addRenderableWidget(itemFramesButton);


        playersButton = Button.builder(
                Component.literal("Show Players: " + OptimizerConfig.showPlayers),
                button -> {
                    OptimizerConfig.showPlayers = !OptimizerConfig.showPlayers;
                    updateButtons();
                }
        ).bounds(10, 130, 200, 20).build();

        addRenderableWidget(playersButton);


        armorStandsButton = Button.builder(
                Component.literal("Show Armor Stands: " + OptimizerConfig.showArmorStands),
                button -> {
                    OptimizerConfig.showArmorStands = !OptimizerConfig.showArmorStands;
                    updateButtons();
                }
        ).bounds(10, 160, 200, 20).build();

        addRenderableWidget(armorStandsButton);


        otherButton = Button.builder(
                Component.literal("Show Other: " + OptimizerConfig.showOther),
                button -> {
                    OptimizerConfig.showOther = !OptimizerConfig.showOther;
                    updateButtons();
                }
        ).bounds(10, 190, 200, 20).build();

        addRenderableWidget(otherButton);


        saveButton = Button.builder(
                Component.literal("Save & Quit"),
                button -> {

                    ConfigManager.save();

                    minecraft.setScreenAndShow(parent);
                }
        ).bounds(10, 230, 100, 20).build();

        addRenderableWidget(saveButton);


        discardButton = Button.builder(
                Component.literal("Discard"),
                button -> {

                    ConfigManager.load();

                    minecraft.setScreenAndShow(parent);
                }
        ).bounds(120, 230, 100, 20).build();

        addRenderableWidget(discardButton);
    }


    private void updateButtons() {

        enabledButton.setMessage(
                Component.literal("HUD Enabled: " + OptimizerConfig.enabled)
        );

        totalEntitiesButton.setMessage(
                Component.literal("Show Entities: " + OptimizerConfig.showTotalEntities)
        );

        itemFramesButton.setMessage(
                Component.literal("Show Item Frames: " + OptimizerConfig.showItemFrames)
        );

        playersButton.setMessage(
                Component.literal("Show Players: " + OptimizerConfig.showPlayers)
        );

        armorStandsButton.setMessage(
                Component.literal("Show Armor Stands: " + OptimizerConfig.showArmorStands)
        );

        otherButton.setMessage(
                Component.literal("Show Other: " + OptimizerConfig.showOther)
        );
    }


    @Override
    public void extractRenderState(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float delta
    ) {

        super.extractRenderState(graphics, mouseX, mouseY, delta);

        Minecraft client = Minecraft.getInstance();

        graphics.text(
                client.font,
                "NordiummOptimizer Settings",
                10,
                10,
                0xFFFFFFFF
        );
    }


    @Override
    public void onClose() {
        minecraft.setScreenAndShow(parent);
    }
}