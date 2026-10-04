# DESIGN.md — BrewCraft Coffee Roastery (Jetpack Compose Material 3 Specification)

This design specification provides strict tokens, architecture standards, component definitions, and asset references for AI coding agents (such as Cursor, Copilot, or Claude Code) and Android engineers implementing the **BrewCraft** application using **Jetpack Compose** and **Material Design 3 (M3)**.

---

## 1. GLOBAL DESIGN TOKENS

### 1.1 Color Palette & Theme Tokens
All colors are derived from the *Artisanal Espresso Material* palette, maintaining full AAA/AA contrast compliance under Material 3 guidelines.

```kotlin
// ui/theme/Color.kt
package com.brewcraft.app.ui.theme

import androidx.compose.ui.graphics.Color

// Primary Brand Tones (Deep Espresso Roast)
val EspressoPrimary = Color(0xFF2B1409)
val EspressoOnPrimary = Color(0xFFFFFFFF)
val EspressoPrimaryContainer = Color(0xFF43281C)
val EspressoOnPrimaryContainer = Color(0xFFB58E7D)
val EspressoPrimaryFixed = Color(0xFFFFDBCD)
val EspressoPrimaryFixedDim = Color(0xFFE9BDAB)

// Secondary & CTA Tones (Warm Amber, Caramel Cream)
val CaramelSecondary = Color(0xFF8B4F20)
val CaramelOnSecondary = Color(0xFFFFFFFF)
val CaramelSecondaryContainer = Color(0xFFFEAF77)
val CaramelOnSecondaryContainer = Color(0xFF784011)
val CaramelSecondaryFixed = Color(0xFFFFDCC6)
val CaramelSecondaryFixedDim = Color(0xFFFFB784)

// Tertiary Tones (Deep Mocha & Golden Accents)
val MochaTertiary = Color(0xFF2C1400)
val MochaOnTertiary = Color(0xFFFFFFFF)
val MochaTertiaryContainer = Color(0xFF4A2600)
val MochaOnTertiaryContainer = Color(0xFFCE8643)
val GoldTertiaryFixed = Color(0xFFFFDCC1)

// Surface & Background Tones (Warm Cream Sand)
val SandSurface = Color(0xFFFDF8F5)
val SandOnSurface = Color(0xFF1C1B1A)
val SandSurfaceVariant = Color(0xFFE6E2DF)
val SandOnSurfaceVariant = Color(0xFF504440)
val SandSurfaceDim = Color(0xFFDED9D6)
val SandSurfaceBright = Color(0xFFFDF8F5)
val SandSurfaceContainerLowest = Color(0xFFFFFFFF)
val SandSurfaceContainerLow = Color(0xFFF8F3F0)
val SandSurfaceContainer = Color(0xFFF2EDEA)
val SandSurfaceContainerHigh = Color(0xFFECE7E4)
val SandSurfaceContainerHighest = Color(0xFFE6E2DF)

// Outline & Borders
val EspressoOutline = Color(0xFF82746F)
val EspressoOutlineVariant = Color(0xFFD4C3BD)

// Semantic States
val ErrorColor = Color(0xFFBA1A1A)
val OnErrorColor = Color(0xFFFFFFFF)
val ErrorContainer = Color(0xFFFFDAD6)
val OnErrorContainer = Color(0xFF93000A)
val SuccessGreen = Color(0xFF2E6B34)
val SuccessGreenContainer = Color(0xFFD7E8D5)
```

```kotlin
// ui/theme/Theme.kt (Material 3 ColorScheme binding)
val BrewCraftLightColorScheme = lightColorScheme(
    primary = EspressoPrimary,
    onPrimary = EspressoOnPrimary,
    primaryContainer = EspressoPrimaryContainer,
    onPrimaryContainer = EspressoOnPrimaryContainer,
    secondary = CaramelSecondary,
    onSecondary = CaramelOnSecondary,
    secondaryContainer = CaramelSecondaryContainer,
    onSecondaryContainer = CaramelOnSecondaryContainer,
    tertiary = MochaTertiary,
    onTertiary = MochaOnTertiary,
    tertiaryContainer = MochaTertiaryContainer,
    onTertiaryContainer = MochaOnTertiaryContainer,
    background = SandSurface,
    onBackground = SandOnSurface,
    surface = SandSurface,
    onSurface = SandOnSurface,
    surfaceVariant = SandSurfaceVariant,
    onSurfaceVariant = SandOnSurfaceVariant,
    surfaceTint = Color(0xFF785748),
    outline = EspressoOutline,
    outlineVariant = EspressoOutlineVariant,
    error = ErrorColor,
    onError = OnErrorColor,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer
)
```

---

### 1.2 Typography (sp & FontWeight)
Standardized against `Inter` / `Roboto` utilizing Material 3 Type Scales.

| Token | Size (`sp`) | Line Height (`sp`) | Weight | Usage |
|---|---|---|---|---|
| `displayLarge` | 36.sp | 44.sp | `FontWeight.Bold` | Hero Onboarding headline (`Crafted Coffee...`) |
| `displayMedium`| 32.sp | 40.sp | `FontWeight.Bold` | Key section highlights |
| `headlineLarge`| 28.sp | 34.sp | `FontWeight.Bold` | Page titles (`Your Coffee Bag`, `Good morning`) |
| `headlineMedium`| 24.sp | 30.sp | `FontWeight.SemiBold`| Product names (`Caramel Macchiato`) |
| `headlineSmall`| 20.sp | 26.sp | `FontWeight.SemiBold`| Module subheaders (`Order Stepper`, `Price Breakdown`) |
| `titleLarge` | 18.sp | 24.sp | `FontWeight.SemiBold`| Card headlines, Pickup pass title |
| `titleMedium` | 16.sp | 22.sp | `FontWeight.Medium` | Option section headers (`Choice of Milk`, `Cup Size`) |
| `titleSmall` | 14.sp | 20.sp | `FontWeight.Medium` | Segmented button labels, chip texts |
| `bodyLarge` | 16.sp | 24.sp | `FontWeight.Normal` | Product descriptive copy |
| `bodyMedium` | 14.sp | 20.sp | `FontWeight.Normal` | Secondary details, addresses, item notes |
| `bodySmall` | 12.sp | 16.sp | `FontWeight.Normal` | Calorie notes, helper hints, captions |
| `labelLarge` | 14.sp | 20.sp | `FontWeight.SemiBold`| Primary CTAs (`Start Your Order`, `Place Order`) |
| `labelMedium` | 12.sp | 16.sp | `FontWeight.Medium` | Badge tags (`EST. 2024`, `100% Arabica`) |
| `labelSmall` | 11.sp | 14.sp | `FontWeight.Medium` | Micro tags, status indicators (`6 mins remaining`) |

---

### 1.3 Spacing & Shapes (dp & CornerRadii)

#### Standard Spacing Units
```kotlin
object BrewCraftSpacing {
    val xxs = 2.dp
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp     // Standard Page Gutter / Horizontal Padding
    val xl = 20.dp
    val xxl = 24.dp    // Section Spacing
    val xxxl = 32.dp   // Major Module Spacing
    val heroPadding = 48.dp
}
```

#### Corner Radii (Shape Scale)
```kotlin
// ui/theme/Shape.kt
val BrewCraftShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),    // Micro chips, tag indicators
    small = RoundedCornerShape(8.dp),         // Small buttons, stepper icons
    medium = RoundedCornerShape(16.dp),       // Food & drink cards, input boxes
    large = RoundedCornerShape(24.dp),        // Featured promo hero banner, QR card container
    extraLarge = RoundedCornerShape(32.dp)    // Bottom sheets, full rounded pills
)

val PillShape = RoundedCornerShape(9999.dp)   // CTAs, segmented control options, status pills
val TopRoundedSheet = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
```

---

## 2. SCREEN REFERENCE GALLERY

| Screen Identifier | Technical Title | Device Form Factor | Structural Purpose & Flow Role |
|---|---|---|---|
| `SCREEN_1` | `Welcome & Intro` | Mobile (390 × 844dp) | **Splash / Onboarding Screen**: Full-bleed background hero with rich espresso atmosphere, brand badge (`EST. 2024`), value proposition pills, primary CTA leading to menu. |
| `SCREEN_2` | `Home & Explore` | Mobile (390 × 844dp) | **Main Discovery Hub**: Top app bar with branch selector and profile avatar, search input, loyalty club progress card, horizontal category chips, seasonal hero promotion, and 2-column popular drink catalog. Fixed bottom navigation bar. |
| `SCREEN_3` | `Coffee Customization` | Mobile (390 × 844dp) | **Product Configurator**: Header hero image with calorie tags, temperature switcher (`Iced` / `Hot`), cup size segmented selectors, shot counter, single-choice milk & sweetness chips, special notes text area, and fixed bottom checkout tray. |
| `SCREEN_4` | `Cart & Checkout` | Mobile (390 × 844dp) | **Transaction Review**: Pickup vs Delivery toggle, editable order list with quantity steppers, applied promo voucher badge, detailed fee breakdown, payment method radio group, and `Place Order` button. |
| `SCREEN_5` | `Live Order Tracking & Rewards` | Mobile (390 × 844dp) | **Fulfillment & Loyalty**: Active order stepper bar (`Received` -> `Crafting` -> `Ready`), Barista status bubble, high-contrast QR digital pickup pass, direct navigation/call store buttons, and loyalty point redemption shelf. |

---

## 3. COMPONENT-LEVEL SPECIFICATIONS

### 3.1 App Navigation Chrome
- **Top App Bar (`CenterAlignedTopAppBar` / `TopAppBar`)**:
    - `ContainerColor`: `SandSurface` (Transparent with `WindowInsets.statusBars` on Onboarding).
    - Navigation Icon: Circular back arrow (`IconButton`) with `Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")`.
    - Actions: Member profile avatar (`AsyncImage` or `Image`) clipped to `CircleShape` with 36.dp diameter and 1.5.dp `SandSurfaceContainerHighest` border.
- **Bottom Navigation Bar (`NavigationBar`)**:
    - `containerColor`: `SandSurfaceContainerLowest`, elevation = 3.dp.
    - Items: `Home`, `Menu`, `Cart` (with badge counting items), `Activity`.
    - Indicator: `CaramelSecondaryFixed` capsule for active tab; icons styled with `EspressoPrimary`.

### 3.2 Containers & Layout Architecture
- **Root Screen Container**:
    - Default: `Scaffold(topBar = { ... }, bottomBar = { ... }, containerColor = MaterialTheme.colorScheme.background)`
    - Inner content: Wrapped in `LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(16.dp))`.
- **Product Card (`BrewProductCard`)**:
    - Container: `Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = SandSurfaceContainerLowest))`.
    - Visual: 1:1 Aspect ratio rounded image (12.dp corner radius).
    - Interaction: Favorite heart icon button in top-right corner; Floating `+` button in `CaramelSecondaryContainer` at bottom-right for instant cart additions.
- **Order Summary Card (`OrderCard`)**:
    - Container: `ElevatedCard` or `Surface` with `SandSurfaceContainerLow` background and 16.dp rounded corners.
    - Left: 64.dp square product thumbnail with 8.dp radius.
    - Center: Title and comma-separated customization summary (`bodySmall`).
    - Right: Quantity controller `Row` featuring `-` and `+` touch targets with centered bold quantity text.
- **Live Pickup QR Pass (`DigitalPickupPass`)**:
    - Container: `Surface(shape = RoundedCornerShape(20.dp), color = SandSurfaceContainerLow)` with centered 180.dp QR code, auto-verified tag, and dual actionable buttons (`Get Directions`, `Call Store`) in a balanced 2-column `Row`.

---

## 4. FIXED IMAGE ASSET REGISTRY

Below are the exact image assets used across the BrewCraft Android application, pre-packaged for `res/drawable/` or local asset stores:

| Asset Name | Target Drawable Resource | Aspect Ratio | Dimensions | Usage in UI |
|---|---|---|---|---|
| **BrewCraft Brand Logo** | `ic_brewcraft_logo.xml` / `brewcraft_logo.png` | 1:1 Vector | 200 × 200 px | App launcher, TopAppBar left logo badge, Onboarding header insignia. |
| **Intro Espresso Hero** | `hero_espresso.png` | 9:16 Portrait | 768 × 1376 px | Full-bleed background on `Welcome & Intro` onboarding screen. |
| **Member Avatar (Alex)** | `user_alex.jpg` | 1:1 Square | 1024 × 1024 px | Profile icon in TopAppBar across all primary screens, Club header. |
| **Iced Caramel Macchiato** | `iced_caramel_macchiato.png` | 1:1 Square | 1024 × 1024 px | Product detail hero, Popular Drinks grid, Cart item thumbnail. |
| **Fresh Almond Croissant** | `almond_croissant.png` | 1:1 Square | 1024 × 1024 px | Cart item thumbnail, Upsell pastry list, Rewards redemption shelf. |
| **Nitro Cold Brew Reserve** | `nitro_cold_brew.png` | 1:1 Square | 1024 × 1024 px | Popular Drinks grid card, Seasonal specials carousel. |
| **Artisanal Flat White** | `flat_white_latte.png` | 1:1 Square | 1024 × 1024 px | Hot coffee category item, Product catalog, Loyalty banner. |

### Android Resource Directory Mapping
```text
app/src/main/res/
  ├── drawable/
  │    ├── ic_brewcraft_logo.xml
  │    ├── hero_espresso.png
  │    ├── user_alex.jpg
  │    ├── iced_caramel_macchiato.png
  │    ├── almond_croissant.png
  │    ├── nitro_cold_brew.png
  │    └── flat_white_latte.png
  └── values/
       ├── colors.xml
       └── strings.xml
```

---

## 5. AI IMPLEMENTATION SPECIAL INSTRUCTIONS (Cursor / Copilot Directive)

When implementing or editing code for this project, AI coding agents MUST strictly enforce the following rules:

```markdown
### AGENT SYSTEM DIRECTIVE FOR JETPACK COMPOSE GENERATION
1. ARCHITECTURE PATTERN:
   - Use unidirectional data flow (UDF) with MVVM.
   - Screen composables must be stateless, receiving `(uiState: ScreenUiState, onAction: (ScreenAction) -> Unit)`.
   - Wrap state collections in `collectAsStateWithLifecycle()` inside lifecycle-aware composables.

2. COLOR & THEME ENFORCEMENT:
   - NEVER use hardcoded hexadecimal colors (e.g. `Color(0xFF...)`) inside Screen or Component composables.
   - ALWAYS reference `MaterialTheme.colorScheme.*` or the designated design tokens (e.g., `CaramelSecondaryContainer`).
   - Primary Call-to-Action (CTA) buttons (`Start Your Order`, `Place Order`, `Add to Cart`) MUST use `ButtonDefaults.buttonColors(containerColor = EspressoPrimary, contentColor = Color.White)` or the warm caramel gradient with 56.dp height and `PillShape`.

3. MATERIAL 3 COMPONENT CONSTRAINTS:
   - Use `SegmentedButton` or `FilterChip` with `FilterChipDefaults.filterChipColors()` for single/multi-selection option rows (e.g. Temperature, Cup Size, Milk).
   - Use `Scaffold` padding values (`innerPadding`) directly on the top-level scrollable container via `Modifier.padding(innerPadding)` to avoid content hiding behind Top/Bottom navigation bars.
   - For lists, strictly prefer `LazyColumn` or `LazyVerticalGrid(columns = GridCells.Fixed(2))` with explicit `contentPadding` and `verticalArrangement = Arrangement.spacedBy(16.dp)`.

4. IMAGE LOADING:
   - Use Coil `AsyncImage(model = R.drawable.<asset_id>, contentDescription = "...", contentScale = ContentScale.Crop)` with proper fallbacks.
   - On the Onboarding Screen, use a layered `Box` with `ContentScale.Crop` on the background image, surmounted by a `Brush.verticalGradient` brush overlay to guarantee 4.5:1 text contrast for all typography.
```
