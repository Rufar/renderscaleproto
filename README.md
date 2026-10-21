# RenderScale Prototype (1.12.2, Forge 14.23.5.2860)

Требования: JDK 8, Gradle 4.4.1 (для ForgeGradle 2.3).

Сборка:
1. В корне проекта выполнить `gradle setupDecompWorkspace` (один раз, скачает Minecraft и маппинги).
2. Затем `gradle build`.
3. Готовый jar: `build/libs/renderscaleproto-0.0.1.jar`. Положить в папку `mods` профиля Forge.

Для запуска в IDE: `gradle genIntellijRuns` или `gradle eclipse`, затем запуск клиента.
