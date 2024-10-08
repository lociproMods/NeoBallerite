package net.locipro.balleritemod.command;



import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.locipro.balleritemod.hydration.HydrationManager;
import net.locipro.balleritemod.hydration.IHydratable;
import net.locipro.balleritemod.util.player.PlayerUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import java.util.Collection;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
public final class HydrationCommands {
    public static void registerHydrationCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal("hydration").requires(serverCommandSource -> serverCommandSource.hasPermission(4))
                        .then(literal("debug")
                                .executes(HydrationCommands::toggleDebug))
                        .then(literal("info")
                                .then(argument("targets", EntityArgument.players())
                                        .executes(context -> HydrationCommands.getHydrationInfo(context, EntityArgument.getPlayers(context, "targets")))))
                        .then(literal("enable")
                                .then(argument("targets", EntityArgument.players())
                                        .executes(context -> HydrationCommands.enableHydration(context, EntityArgument.getPlayers(context, "targets")))))
                        .then(literal("disable")
                                .then(argument("targets", EntityArgument.players())
                                        .executes(context -> HydrationCommands.disableHydration(context, EntityArgument.getPlayers(context, "targets")))))
                        .then(literal("add")
                            .then(argument("targets", EntityArgument.players())
                                .then(argument("hydration", integer())
                                    .executes(context -> HydrationCommands.addHydration(context, EntityArgument.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "hydration"))))))
                       .then(literal("remove")
                            .then(argument("targets", EntityArgument.players())
                                 .then(argument("hydration", integer())
                                     .executes(context -> HydrationCommands.removeHydration(context, EntityArgument.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "hydration"))))))
                       .then(literal("set")
                             .then(argument("targets", EntityArgument.players())
                                 .then(argument("hydration", integer())
                                      .executes(context -> HydrationCommands.setHydration(context, EntityArgument.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "hydration"))))))
                        .then(literal("multiplier")
                                .then(argument("targets", EntityArgument.players())
                                        .then(argument("multiplier", integer())
                                                .executes(context -> HydrationCommands.setThirstMultiplier(context, EntityArgument.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "multiplier")))))));
    }

    private static int getHydrationInfo(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) {
        for (ServerPlayer player : players) {
            HydrationManager hm = ((IHydratable)player).getHydrationManager();

            boolean hasThirst = hm.hasThirst();
            int hydration = hm.getHydration();
            int maxHydration = hm.getMaxHydration();
            int multiplier = hm.getThirstTickMultiplier();

            context.getSource().sendSuccess(() -> Component.literal("Hydration info for player '" + PlayerUtil.getPlayerName(player) + "' :"), true);
            context.getSource().sendSuccess(() -> Component.literal("hasThirst = " + hasThirst), true);
            context.getSource().sendSuccess(() -> Component.literal("hydration =  " + hydration), true);
            context.getSource().sendSuccess(() -> Component.literal("maxHydration = " + maxHydration), true);
            context.getSource().sendSuccess(() -> Component.literal("multiplier = " + multiplier), true);
            context.getSource().sendSuccess(() -> Component.literal("-----------------------------------"), true);
        }
        return 1;
    }

    private static int addHydration(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players, int amount) throws CommandSyntaxException {
        for (ServerPlayer player: players) {
            HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();
            if (amount < 0) {
                throw new SimpleCommandExceptionType(Component.literal("Cannot add a negative value to " + PlayerUtil.getPlayerName(player) + "'s hydration!")
                        .withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD)).create();
            }
            if (amount + hydrationManager.getHydration() > 10) {
                hydrationManager.setHydration(10);
                context.getSource().sendSuccess(() -> Component.literal("Hydration of player '" + PlayerUtil.getPlayerName(player) + "' after incrementing " + amount + " is more than its maximum, therefore player's hydration has been set to its maximum."), true);
                //HydrationHelper.syncHydration(context.getSource().getPlayerOrThrow());
            }else {
                hydrationManager.addHydration(amount);
                context.getSource().sendSuccess(() -> Component.literal("Hydration of player '" + PlayerUtil.getPlayerName(player) + "' has been incremented by " + amount), true);
                //HydrationHelper.syncHydration(context.getSource().getPlayerOrThrow());
            }
        }
        return 1;

    }
    private static int removeHydration(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players, int amount) throws CommandSyntaxException{
        for (ServerPlayer player : players) {
            HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();

            if (amount < 0) {
                throw new SimpleCommandExceptionType(Component.literal("Cannot remove a negative value from " + PlayerUtil.getPlayerName(player) + "'s hydration!")
                        .withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD)).create();
            }
            if (hydrationManager.getHydration() - amount < 0) {
                hydrationManager.setHydration(0);
                context.getSource().sendSuccess(() -> Component.literal("Hydration of player '" + PlayerUtil.getPlayerName(player) + "' after decrementing " + amount + " is less than its minimum, therefore player's hydration has been set to its minimum."), true);
                //HydrationHelper.syncHydration(context.getSource().getPlayerOrThrow());
            }else {
                hydrationManager.removeHydration(amount);
                context.getSource().sendSuccess(() -> Component.literal("Hydration of player '" + PlayerUtil.getPlayerName(player) + "' has been decremented by " + amount), true);
                //HydrationHelper.syncHydration(context.getSource().getPlayerOrThrow());
            }
        }
        return 1;
    }
    private static int setHydration(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players, int amount) throws CommandSyntaxException{
        for (ServerPlayer player : players) {
            HydrationManager hydrationManager = ((IHydratable) player).getHydrationManager();
            int maxHydration = hydrationManager.getMaxHydration();

            if (amount < 0 || amount > maxHydration) {
                throw new SimpleCommandExceptionType(Component.literal("Value " + amount + " is out of bounds for hydration. Choose a value between 0 and " + maxHydration)
                        .withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD)).create();
            }
            hydrationManager.setHydration(amount);
            context.getSource().sendSuccess(() -> Component.literal("Hydration of player '" + PlayerUtil.getPlayerName(player) + "' has been set to " + amount), true);
            //HydrationHelper.syncHydration(context.getSource().getPlayerOrThrow());
        }
        return 1;
    }

    private static int setThirstMultiplier(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players, int amount) throws CommandSyntaxException {
        for (ServerPlayer player : players) {
            HydrationManager hydrationManager = ((IHydratable) player).getHydrationManager();

            if (hydrationManager.hasThirst()) {
                if (amount < 1) {
                    throw new SimpleCommandExceptionType(Component.literal("Value " + amount + " is out of bounds for thirst multiplier. Cannot be less than 1.")
                            .withStyle(ChatFormatting.RED).withStyle(ChatFormatting.BOLD)).create();
                }
                hydrationManager.setThirstTickMultiplier(amount);
                context.getSource().sendSuccess(() -> Component.literal("Thirst multiplier of player '" + PlayerUtil.getPlayerName(player) + "' has been set to " + amount), true);
                return 1;
            }
            throw new SimpleCommandExceptionType(Component.literal("Player does not have thirst, cannot change multiplier")).create();
            //HydrationHelper.syncHydration(context.getSource().getPlayerOrThrow());
        }
        return 0;
    }

    private static int enableHydration(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) throws CommandSyntaxException {
        for (ServerPlayer player : players) {
            HydrationManager hydrationManager = ((IHydratable) player).getHydrationManager();
            if (hydrationManager.hasThirst()) {
                throw new SimpleCommandExceptionType(Component.literal("Hydration already enabled for player '" + PlayerUtil.getPlayerName(player) + "'")).create();
            }
            hydrationManager.setThirstBool(true);
            context.getSource().sendSuccess(() -> Component.literal("Hydration enabled for player '" + PlayerUtil.getPlayerName(player) + "'"), true);
        }
        return 1;
    }

    private static int disableHydration(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) throws CommandSyntaxException {
        for (ServerPlayer player : players) {
            HydrationManager hydrationManager = ((IHydratable) player).getHydrationManager();
            if (!hydrationManager.hasThirst()) {
                throw new SimpleCommandExceptionType(Component.literal("Hydration already disabled for player '" + PlayerUtil.getPlayerName(player) + "'")).create();
            }
            hydrationManager.setThirstBool(false);
            context.getSource().sendSuccess(() -> Component.literal("Hydration disabled for player '" + PlayerUtil.getPlayerName(player) + "'"), true);
        }
        return 1;
    }

    //TODO figure this shit uot
    private static int toggleDebug(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (context.getSource().getPlayerOrException() != null) {
            ServerPlayer player = context.getSource().getPlayerOrException();
            HydrationManager hydrationManager = ((IHydratable)player).getHydrationManager();

            hydrationManager.toggleDebug();
        }
        return 1;
    }
}
