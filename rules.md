# BrewCraft Coffee Ordering Android Project Rules

You are an Expert Senior Android Developer.

Build this project as a production-quality, compile-ready, responsive native
Android coffee ordering and pickup application using:

- Kotlin
- Jetpack Compose
- Material 3
- Single-Activity architecture
- MVVM
- Unidirectional Data Flow (UDF)
- immutable UI state
- StateFlow
- lifecycle-aware state collection
- Compose Navigation where navigation is already present or required

The current BrewCraft visual references, the uploaded `design.md`, the real
files inside the project's local `assets` folder, the existing Android source
code, and the supplied Stitch HTML references define this project.

Do not import product assumptions from the previous e-commerce project.
The previous `rules.md` is a structural example only.

Follow these rules strictly.

---

# 1. SOURCE OF TRUTH

Before implementing or modifying a screen, inspect all available current
project evidence for that screen.

Use this priority order:

1. Current supplied `screen.png` visual reference for:
    - composition;
    - hierarchy;
    - visible text;
    - visible numbers and prices;
    - selected state;
    - element placement;
    - relative size;
    - image crop;
    - initial screen state.

2. The uploaded root `design.md` for:
    - official Android color tokens;
    - typography;
    - spacing;
    - shapes;
    - Material 3 usage;
    - architecture rules;
    - component conventions.

3. The real local project assets for:
    - exact filenames;
    - real image content;
    - available aspect ratios;
    - local-only image implementation.

4. Existing Android source code where it already matches the current design.

5. Supplied Stitch `code.html` files for:
    - interaction intent;
    - deterministic demo values;
    - control behavior;
    - UI state transitions;
    - copy that is not clearly readable from the visual reference.

If sources conflict, resolve the conflict using the same priority order.

Important conflict rules:

- The visual reference wins for what the initial screen must look like.
- `design.md` wins for Android theme tokens and architecture.
- Real local assets win over asset names merely mentioned in documentation.
- The uploaded root `design.md` wins over the embedded Stitch design document
  if the two files differ.
- HTML JavaScript is behavior reference, not production business logic.
- Do not reproduce an obvious HTML demo bug when it contradicts the supplied
  visual reference.

Never silently download or recreate a missing source asset from the web.

---

# 2. PROJECT IDENTITY

This project is:

**BrewCraft — a specialty coffee ordering, customization, checkout, pickup,
order tracking, and loyalty mobile app.**

The grounded current screen references supplied in the Stitch archive are:

1. `home_explore/screen.png`
    - Home & Explore

2. `coffee_customization/screen.png`
    - Drink Customizer / Coffee Customization

3. `cart_checkout/screen.png`
    - Cart & Checkout

4. `live_order_tracking_rewards/screen.png`
    - Live Order Tracking & Rewards

The uploaded `design.md` also describes a **Welcome & Intro / Onboarding**
screen. However, the supplied Stitch screen archive does not currently include
a matching onboarding `screen.png`, and the local asset-folder screenshot does
not establish the documented onboarding hero image.

Therefore:

- do not invent the onboarding screen from imagination;
- do not make onboarding the start destination unless a real reference is
  supplied later or an existing implementation already establishes it;
- when no existing app flow says otherwise, use the grounded Home & Explore
  screen as the practical demo start destination.

Do not invent additional screens such as:

- login;
- registration;
- account creation;
- forgot password;
- delivery address management;
- payment setup;
- full profile settings;
- notification center;
- store chooser;
- menu catalog;
- favorites;
- reward details;
- order history;
- onboarding variants;
- splash workflows.

A visible navigation item is not permission to design a missing screen.

---

# 3. STITCH HTML IS A REFERENCE ONLY

The supplied `code.html` files are not Android production code.

Never use:

- WebView for these screens;
- HTML UI;
- Tailwind;
- CSS;
- JavaScript UI;
- web Material Symbols fonts;
- remote Google-hosted reference images;
- `lh3.googleusercontent.com` images;
- `aida-public` images;
- remote Google Fonts at runtime.

Do not copy HTML into Android.

Translate the intended design into native Jetpack Compose.

Translate HTML interaction behavior into Kotlin state and actions.

Examples include:

- selected category;
- favorite state;
- add-to-cart feedback;
- serving style;
- cup size;
- shot count;
- milk selection;
- sweetness selection;
- ice selection;
- quantity;
- barista notes;
- pickup/delivery selection;
- cart quantities;
- cart removal;
- voucher state;
- payment selection;
- order submission state;
- receipt feedback;
- help/call/directions actions.

No JavaScript state may remain in the Android implementation.

---

# 4. OFFICIAL DESIGN SYSTEM

Use the uploaded `design.md` as the official Android design-token source.

Do not hardcode ad-hoc colors, shapes, or typography inside screen composables.

## 4.1 Core color roles

The current official BrewCraft light theme includes:

- `primary` = `#2B1409`
- `onPrimary` = `#FFFFFF`
- `primaryContainer` = `#43281C`
- `onPrimaryContainer` = `#B58E7D`
- `secondary` = `#8B4F20`
- `onSecondary` = `#FFFFFF`
- `secondaryContainer` = `#FEAF77`
- `onSecondaryContainer` = `#784011`
- `tertiary` = `#2C1400`
- `tertiaryContainer` = `#4A2600`
- `background` = `#FDF8F5`
- `surface` = `#FDF8F5`
- `surfaceContainerLowest` = `#FFFFFF`
- `surfaceContainerLow` = `#F8F3F0`
- `surfaceContainer` = `#F2EDEA`
- `surfaceContainerHigh` = `#ECE7E4`
- `surfaceContainerHighest` = `#E6E2DF`
- `onSurface` = `#1C1B1A`
- `onSurfaceVariant` = `#504440`
- `outline` = `#82746F`
- `outlineVariant` = `#D4C3BD`
- `error` = `#BA1A1A`

Use `MaterialTheme.colorScheme.*` wherever a Material role exists.

Project-specific theme constants may be referenced from the theme package, but
do not duplicate raw hex values throughout the app.

## 4.2 Typography

Use the type scale defined by `design.md`.

Primary roles include:

- `headlineLarge`: 28sp / 34sp / Bold
- `headlineMedium`: 24sp / 30sp / SemiBold
- `headlineSmall`: 20sp / 26sp / SemiBold
- `titleLarge`: 18sp / 24sp / SemiBold
- `titleMedium`: 16sp / 22sp / Medium
- `titleSmall`: 14sp / 20sp / Medium
- `bodyLarge`: 16sp / 24sp / Normal
- `bodyMedium`: 14sp / 20sp / Normal
- `bodySmall`: 12sp / 16sp / Normal
- `labelLarge`: 14sp / 20sp / SemiBold
- `labelMedium`: 12sp / 16sp / Medium
- `labelSmall`: 11sp / 14sp / Medium

The references use Inter-style typography.

If the project does not contain a bundled Inter font, do not fetch it at
runtime. Use the existing Android/system sans-serif typography until a local
font file is explicitly supplied.

## 4.3 Spacing

Use the BrewCraft spacing scale:

- 2dp
- 4dp
- 8dp
- 12dp
- 16dp
- 20dp
- 24dp
- 32dp
- 48dp hero padding where appropriate

The standard horizontal page gutter is 16dp unless the visual reference shows
a clearly different local treatment.

## 4.4 Shapes

Use the current shape system:

- 4dp: micro elements;
- 8dp: small controls;
- 16dp: cards and inputs;
- 24dp: hero/promo containers;
- 32dp: large expressive surfaces;
- full pill: `RoundedCornerShape(9999.dp)`;
- top rounded sheet: 28dp top corners.

Do not flatten the design into generic rectangular Material defaults.

---

# 5. CURRENT LOCAL ASSET REGISTRY

The current asset-folder screenshot establishes these local image filenames:

1. `almond_croissant.png`
2. `banner.jpg`
3. `caramel_macchiato.jpg`
4. `caramel_macchiato.png`
5. `iced_spanish_latte.jpg`
6. `nitro_cold_brew.png`
7. `oat_milk_flat_white.png`
8. `profile.png`

These filenames are the current local source of truth.

Before using an image in code:

1. inspect the actual file in the project;
2. verify its dimensions;
3. verify its content;
4. match it semantically to the current visual reference;
5. choose `ContentScale` and crop from the target reference, not by guess.

Do not rename source assets unnecessarily.

If Android-safe resource normalization is required, preserve a clear mapping
between the original asset filename and the imported drawable resource.

---

# 6. ASSET SEMANTIC MAPPING

## `almond_croissant.png`

Grounded role:

- Almond Croissant product image;
- Checkout cart item thumbnail;
- pastry/reward presentation only when the supplied reference calls for that
  same product.

Do not use it as a generic bakery background.

## `banner.jpg`

Grounded role:

- main Home & Explore seasonal promotional hero when its actual content matches
  the supplied promo reference.

Do not automatically reuse it for unrelated photo cards.

## `caramel_macchiato.jpg`

Grounded role:

- Caramel Macchiato photography.

## `caramel_macchiato.png`

Grounded role:

- Caramel Macchiato photography.

There are two grounded Caramel Macchiato files.

Do not arbitrarily delete one or assume they are duplicates without inspection.
Use the version whose dimensions, crop, and content best match the specific
reference slot, such as:

- Home product card;
- Drink Customizer hero;
- Checkout thumbnail.

The visual reference decides the final crop.

## `iced_spanish_latte.jpg`

Grounded role:

- Iced Spanish Latte card on Home & Explore.

## `nitro_cold_brew.png`

Grounded role:

- Nitro Cold Brew card on Home & Explore.

## `oat_milk_flat_white.png`

Grounded role:

- Oat Milk Flat White card on Home & Explore.

## `profile.png`

Grounded role:

- Alex/profile avatar used in the primary app chrome.

Do not use `profile.png` as Barista Liam's portrait unless the real project
explicitly establishes that they are the same person.

---

# 7. DOCUMENTED ASSETS THAT ARE NOT CURRENTLY LOCAL

The uploaded `design.md` mentions some filenames that are not established by
the supplied current asset-folder screenshot, including examples such as:

- `ic_brewcraft_logo.xml`
- `brewcraft_logo.png`
- `hero_espresso.png`
- `user_alex.jpg`
- `iced_caramel_macchiato.png`
- `flat_white_latte.png`

Do not assume these files exist merely because they appear in documentation.

When documentation and the real asset folder disagree, the real local folder
wins.

Do not download the missing documented assets.

---

# 8. BREWCRAFT LOGO RULE

The current local asset screenshot does not establish a dedicated BrewCraft
logo bitmap/vector.

The supplied visual references already show a simple coffee-symbol brand mark
inside the top app bar.

Therefore, until a real logo asset is supplied:

- build the mark natively with an appropriate Material coffee/local-cafe icon;
- place it in the same small rounded tonal container shown by the references;
- render the `BrewCraft` wordmark as text where visible;
- match the visual reference proportions.

Do not use the remote HTML logo URL.

Do not invent a different brand symbol.

---

# 9. PROFILE AND BARISTA IMAGE RULES

Use `profile.png` for Alex's visible profile/avatar treatment.

The current local asset evidence does not establish a separate Barista Liam
portrait.

Therefore, for Barista Liam:

- do not download the remote HTML portrait;
- do not silently reuse `profile.png`;
- preserve the avatar slot dimensions;
- use a deterministic local placeholder such as initials `L`, `BL`, or a
  neutral person/barista icon until a real local asset is supplied.

---

# 10. HOME & EXPLORE SCREEN

The Home & Explore visual reference is the source of truth for composition and
initial content.

The screen should include, in order:

1. Top app bar:
    - BrewCraft native mark;
    - `BrewCraft • Explore`;
    - `Downtown Roastery, 5th Ave` branch row;
    - notification action;
    - `profile.png` avatar.

2. Greeting/status:
    - `Good morning, Alex ☕`;
    - `Tier Gold` badge;
    - `Ready in ~10 mins at Downtown Roastery`.

3. Search bar:
    - placeholder `Search coffee, roast, pastries...`;
    - search icon;
    - voice icon;
    - filter icon.

4. BrewCraft Club card:
    - current value `140 / 200`;
    - `60 beans until your free cup`;
    - 70% progress;
    - `Next reward: Handcrafted Pourover`;
    - `View perks →`.

5. Explore Categories horizontal chip row:
    - All;
    - Espresso;
    - Cold Brew;
    - Pourover;
    - Signature Lattes;
    - Pastries.

6. Seasonal promotional hero:
    - `Limited Edition`;
    - `20% OFF TODAY`;
    - `Autumn Maple Latte`;
    - visible description;
    - `$4.95`;
    - previous price `$6.20`;
    - `Try Now`.

7. Popular Drinks two-column grid:
    - Caramel Macchiato — `$4.85`;
    - Iced Spanish Latte — `$5.20`;
    - Nitro Cold Brew — `$4.50`;
    - Oat Milk Flat White — `$4.95`.

8. Bottom navigation:
    - Home;
    - Menu;
    - Cart with initial badge `2`;
    - Activity.

Home is initially selected.

## 10.1 Home interaction rules

Grounded interactions from the HTML reference:

- category chips have single selected state;
- `All` is selected initially;
- favorite hearts toggle selected/unselected;
- add buttons give immediate visual feedback;
- add buttons may show a short snackbar/toast-style confirmation.

The Stitch demo changes category selection but does not define a real filtering
algorithm. Do not invent search/filter business logic unless the Android project
already has a product data model that supports it.

The visual reference contains several clickable-looking controls whose final
screens are not supplied, including:

- branch selector;
- notification button;
- View perks;
- Try Now;
- See all;
- Menu tab.

Represent them as actions in UDF if useful, but do not invent destination UI.

If the app is wired as a functional multi-screen demo, selecting the grounded
Caramel Macchiato product may navigate to the supplied Drink Customizer screen.
Do not create unsupported product-detail variants for products without a
reference.

---

# 11. DRINK CUSTOMIZER SCREEN

The Drink Customizer reference is specifically for **Caramel Macchiato**.

Initial grounded state:

- product: `Caramel Macchiato`;
- base price: `$4.85`;
- rating: `4.9`;
- review count: `1,240 reviews`;
- calories: `180 kcal`;
- metadata: `100% Arabica`;
- metadata: `Medium Roast`;
- serving style: `Iced` selected;
- cup size: `Medium` selected;
- medium size surcharge: `+$0.60`;
- shots: `2`;
- milk: `Oat Milk (+$0.50)` selected;
- sweetness: `50% Standard` selected;
- ice: `Less Ice` selected;
- quantity: `1`;
- barista notes: empty;
- initial CTA price: `$5.95`.

## 11.1 Serving style

Supported reference states:

- Iced;
- Hot.

When Hot is selected:

- hide the Ice Level section;
- keep the rest of the customization state unless the reference explicitly
  requires a reset.

When Iced is selected:

- show Ice Level.

## 11.2 Cup size

Grounded sizes:

- Small — 8 oz — no surcharge;
- Medium — 12 oz — `+$0.60`;
- Large — 16 oz — `+$1.20`.

Only one size may be selected.

## 11.3 Espresso shots

Grounded range:

- minimum: 1;
- default: 2;
- maximum: 4.

Grounded labels:

- 1: `Single Espresso (Mild)`;
- 2: `Double Ristretto (Balanced)`;
- 3: `Triple Bold (Robust)`;
- 4: `Quad Turbo (Extra Strong)`.

The Stitch pricing reference changes price by `$0.80` per shot relative to the
2-shot default.

Keep the pricing logic deterministic and centralized.

## 11.4 Milk

Single-choice milk options:

- Oat Milk — `+$0.50`;
- Almond Milk — `+$0.50`;
- Whole Milk — `+$0.00`;
- Skim Milk — `+$0.00`;
- Coconut Milk — `+$0.50`.

Only one may be selected.

## 11.5 Sweetness

Single-choice options:

- No Sugar;
- 25% Mild;
- 50% Standard;
- 100% Sweet.

The supplied reference defines no price change for sweetness.

## 11.6 Ice level

Single-choice options:

- No Ice;
- Less Ice;
- Regular Ice.

The supplied reference defines no price change for ice level.

## 11.7 Barista notes

Provide the optional notes input with the reference placeholder:

`e.g. Extra hot, light caramel drizzle, extra napkin...`

Use local UI state.

Do not add server validation or unsupported character-limit UI.

## 11.8 Quantity and price

Grounded quantity range:

- minimum 1;
- maximum 10.

Reference pricing model:

`unitPrice = basePrice + sizeSurcharge + milkSurcharge + shotAdjustment`

`totalPrice = unitPrice * quantity`

Keep price calculation outside the composable in a deterministic state/domain
layer where practical.

The UI must always display the price derived from the current state.

## 11.9 Add to Cart

The CTA is:

`Add to Cart`

If the project is functioning across screens, this action should update the
shared cart state and provide local confirmation feedback.

Do not create a remote cart API.

---

# 12. CART & CHECKOUT SCREEN

Initial visible reference state:

- fulfillment mode: Pickup;
- Pickup timing: `10–15 mins`;
- Delivery timing: `25–30 mins`;
- location badge: `Downtown Roastery`;
- item count: 2.

Initial items:

1. Caramel Macchiato
    - customization summary: `Medium • Oat Milk • 50% Sweetness • Less Ice`;
    - unit/display price: `$5.95`;
    - quantity: 1.

2. Almond Croissant
    - summary: `Warm & toasted • Butter glaze`;
    - price: `$3.80`;
    - quantity: 1.

Grounded quantity range in the Stitch behavior:

- minimum 1;
- maximum 10.

## 12.1 Fulfillment

Supported reference states:

- Pickup;
- Fast Delivery.

Grounded fees:

- Pickup Packaging & Prep: `$0.50`;
- Delivery & Dispatch Fee: `$2.50`.

Do not create delivery-address screens or location permissions from this toggle.

## 12.2 Voucher

Visible demo voucher:

`BREWFIRST`

Initial discount:

`-$2.00`

Treat this as deterministic local demo behavior unless a real backend is later
introduced.

Do not accept or validate coupons through a network service.

Do not invent a coupon catalog.

## 12.3 Price breakdown

The initial visual reference shows:

- Subtotal: `$9.75`;
- Pickup Packaging & Prep: `$0.50`;
- Promo Discount: `-$2.00`;
- Estimated Tax (8.5%): `$0.70`;
- Total Amount: `$8.95`.

The supplied HTML JavaScript excludes the fulfillment fee from the taxable
amount, which would not reproduce the visible initial reference totals.

For this project, the visible screen reference wins.

Use this deterministic demo calculation so the initial reference values remain
stable:

`taxable = max(0, subtotal + fulfillmentFee - discount)`

`tax = taxable * 0.085`

`total = max(0, subtotal + fulfillmentFee - discount + tax)`

Round currency to two decimals for display.

Do not allow the first recomposition or user interaction to cause the visible
`$8.95` total to jump because of copied demo-JavaScript arithmetic.

## 12.4 Payment method

Grounded methods:

- BrewCraft Balance — selected initially;
- available balance: `$24.50`;
- Apple Pay / Google Pay;
- Mastercard ending in 4242;
- expiry text: `09/27`.

These are demo UI methods.

Do not integrate real payments, wallet SDKs, card storage, or financial APIs
unless explicitly requested later.

Only one payment method may be selected.

## 12.5 Place Order

Primary CTA:

`Place Order`

Initial CTA amount:

`$8.95`

Submitting the order may show a deterministic local loading/success state.

Do not claim that a real payment or order was transmitted.

The HTML success order number is demo content and does not have to be treated as
a global backend identifier across other independently mocked screens.

---

# 13. LIVE ORDER TRACKING & REWARDS SCREEN

Treat this screen as its own deterministic supplied demo state unless the user
explicitly asks to connect checkout output to it.

Do not silently rewrite its content to match the checkout screen.

Initial visible state includes:

- title: `Your Brew is in Motion`;
- `Live` badge;
- order: `#BC-8924`;
- `6 mins remaining`;
- `Pickup Bar 2`.

Order progress steps:

1. Order Received — completed;
2. Brewing & Crafting — active;
3. Ready for Pickup — pending;
4. Enjoy! — pending.

Barista card:

- `Barista Liam`;
- reference status copy about preparing the drink.

Use the missing-barista-image rule from this file.

## 13.1 Digital Pickup Pass

Visible reference content:

- `Digital Pickup Pass`;
- `Scan at Counter`;
- QR-style pass visual;
- `1x Iced Oat Honey Latte`;
- `Large (20oz) • Extra espresso shot • Light ice`;
- `Auto-verified at counter`.

The QR visual must be local and deterministic.

Do not load a QR image from a remote URL.

If the existing project already has a QR-code generator dependency, it may be
used with deterministic local demo data.

Otherwise, preserve the reference layout with a local vector/Canvas treatment
without adding unnecessary network dependencies.

Do not claim the code is connected to a real store backend.

## 13.2 Store actions

Visible reference store data:

- `Downtown Roastery`;
- `412 5th Ave, New York`;
- `0.3 mi away`;
- `Get Directions`;
- `Call Store`.

The HTML uses fake alert behavior.

In Android:

- keep these as explicit user actions;
- do not request background location;
- do not request phone permission just to reproduce the reference;
- if external intents are not already part of the project, local demo feedback
  is acceptable until integration is explicitly requested.

## 13.3 Rewards

Visible demo values:

- `Alex's Bean Rewards`;
- `148 Total Beans`;
- `+12 beans brewing right now!`;
- `Gold Tier Unlocks at 200`;
- `52 beans away`.

Visible rewards:

- Free Flavor Shot — 30 Beans;
- Artisan Pastry — 80 Beans.

Do not create a reward backend, purchase flow, or hidden reward catalog.

## 13.4 Receipt and help

Visible actions:

- `Download Order Receipt (PDF)`;
- `Need Help or Report an Issue?`.

The HTML only simulates these actions.

Therefore:

- do not implement real PDF generation unless explicitly requested;
- do not claim a file was actually downloaded when only reproducing the demo;
- do not invent a support backend;
- local success/snackbar feedback is sufficient for the reference implementation.

---

# 14. LIVE TRACKER PROMOTIONAL PHOTO RULE

The Live Order Tracking reference contains a lower promotional media card with
copy similar to:

`Roasted fresh this morning in-house`

The current asset-folder evidence does not establish a dedicated matching
roastery photograph for that specific card.

Do not download the remote HTML image.

Do not automatically reuse an unrelated product photo.

Preferred order:

1. use a real local image only if inspection confirms that it semantically and
   visually matches the reference;
2. otherwise preserve the card dimensions, typography, overlay, and hierarchy
   with a native tonal/gradient placeholder until a correct local asset is
   supplied.

---

# 15. NATIVE ANDROID ARCHITECTURE

Use:

- Kotlin;
- Jetpack Compose;
- Material 3;
- Single Activity;
- MVVM;
- UDF;
- immutable UI state;
- StateFlow;
- `collectAsStateWithLifecycle()`;
- lifecycle-aware ViewModels;
- Compose Navigation when navigation is part of the existing project.

Prefer this screen boundary:

```kotlin
@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onNavigate: (Destination) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onAction = viewModel::onAction,
    )
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    onAction: (HomeAction) -> Unit,
) {
    // Stateless rendering only.
}
```

Apply the same pattern to:

- Home;
- Drink Customizer;
- Cart & Checkout;
- Live Order Tracking & Rewards.

Do not let screen composables directly own business state that must survive
recomposition or navigation.

---

# 16. STATE MODELING RULES

Use explicit state instead of scattered booleans wherever possible.

Examples:

```kotlin
enum class ServingStyle { Iced, Hot }
enum class CupSize { Small, Medium, Large }
enum class Sweetness { NoSugar, Mild25, Standard50, Sweet100 }
enum class IceLevel { NoIce, LessIce, RegularIce }
enum class FulfillmentMode { Pickup, Delivery }
```

Model screen actions explicitly.

Examples:

```kotlin
sealed interface CustomizerAction {
    data class SelectServingStyle(val value: ServingStyle) : CustomizerAction
    data class SelectSize(val value: CupSize) : CustomizerAction
    data class ChangeShots(val delta: Int) : CustomizerAction
    data class SelectMilk(val id: String) : CustomizerAction
    data class SelectSweetness(val value: Sweetness) : CustomizerAction
    data class SelectIce(val value: IceLevel) : CustomizerAction
    data class ChangeNotes(val value: String) : CustomizerAction
    data class ChangeQuantity(val delta: Int) : CustomizerAction
    data object AddToCart : CustomizerAction
}
```

These examples define architectural shape, not mandatory exact class names.
Preserve existing naming conventions when the project already has them.

---

# 17. SHARED CART STATE

If the Android project is intended to operate as a connected demo instead of
four isolated screenshots, use one shared local cart source of truth.

Acceptable approaches include:

- an application-scoped in-memory repository;
- a navigation-graph-scoped shared ViewModel;
- the project's existing repository pattern.

Do not add a database solely to reproduce the supplied demo unless persistence
is already a project requirement.

When shared cart behavior is enabled:

- Home add buttons update cart state;
- Customizer Add to Cart updates cart state;
- cart badge reflects the cart item quantity/count;
- Cart screen renders from the same state;
- quantity/removal updates totals deterministically.

Preserve the supplied initial demo state when no previous user interaction has
occurred.

---

# 18. NAVIGATION RULES

The supplied sources do not define an authoritative Android route naming
scheme.

Therefore:

- preserve existing routes if the Android project already has them;
- do not rename package routes merely to match an invented convention;
- do not create destination screens that lack a supplied visual reference;
- back actions must use the navigation stack;
- Home is the practical default start screen when no existing flow says
  otherwise and no onboarding reference is available.

Bottom navigation labels must match the visual reference:

- Home;
- Menu;
- Cart;
- Activity.

Because a standalone Menu screen is not currently supplied, do not design one
from scratch just to satisfy the tab label.

---

# 19. IMAGE IMPLEMENTATION RULES

All production images must be local.

For local Android drawables:

- `Image(painterResource(...))` is preferred when no image-loader dependency is
  otherwise needed;
- if the project already uses Coil, local `R.drawable` models are acceptable;
- do not add Coil solely to display static local resources unless the project
  already benefits from it.

Always set:

- correct `contentDescription` when meaningful;
- `null` content description for purely decorative imagery;
- the `ContentScale` that matches the reference crop;
- clipping before/with image rendering as appropriate.

Do not use:

- placeholder image websites;
- generated remote image URLs;
- reference CDN URLs;
- network-loaded profile images;
- network-loaded product images.

---

# 20. MATERIAL ICON RULES

Replace web Material Symbols with Android-native Material icons or local vector
resources.

Choose the closest semantically equivalent icon.

Do not add a custom icon dependency simply to reproduce one minor web glyph if
a standard Material icon is visually sufficient.

Icons are secondary to layout fidelity.

---

# 21. LAYOUT AND RESPONSIVENESS

The reference screens are mobile portrait designs.

Match the supplied mobile composition first, while remaining responsive.

Use:

- `Scaffold`;
- `LazyColumn` for long vertical content;
- `LazyVerticalGrid(GridCells.Fixed(2))` for the Home Popular Drinks grid when
  it preserves the reference;
- `LazyRow` or horizontally scrollable Row for category chips;
- `WindowInsets` / Scaffold padding for system bars;
- sticky/fixed bottom action areas implemented with Compose layout, not raw
  pixel offsets.

Avoid:

- hardcoded full-screen pixel heights;
- absolute positioning tied to one screenshot height;
- content hidden beneath system navigation;
- nested scrolling structures that create broken gesture behavior.

Long screenshots are references for complete content, not a requirement to set
the device viewport to 1600px height.

---

# 22. APP BAR AND BOTTOM BAR RULES

## App bars

Use the reference-specific chrome.

Home:

- no back arrow;
- brand/location block on the left;
- notification + profile on the right.

Customizer, Checkout, and Live Tracker:

- back arrow;
- BrewCraft mark;
- screen title;
- overflow action;
- profile avatar.

Preserve truncation behavior for long titles rather than shrinking text to
unreadable sizes.

## Bottom navigation

Only the Home reference currently shows the fixed four-item bottom navigation.

Do not add the same bottom bar to Customizer, Checkout, or Live Tracker unless
an actual reference or existing app architecture requires it.

---

# 23. STICKY ACTION BAR RULES

Customizer and Checkout contain sticky bottom action areas.

They must:

- remain visible above the system navigation inset;
- not cover scrollable content;
- provide sufficient bottom content padding in the scroll container;
- match the reference tonal surface and pill CTA treatment;
- derive displayed price from current state.

Do not fake sticky behavior with a large fixed spacer alone.

---

# 24. ACCESSIBILITY

Minimum touch targets should be at least 48dp where practical.

Provide meaningful accessibility labels for:

- back;
- notification;
- profile;
- favorite;
- add to cart;
- quantity decrement/increment;
- payment selection;
- order-progress steps;
- call/directions;
- receipt/help actions.

Do not rely only on color to communicate selected state.

Use:

- checkmarks;
- icons;
- text state;
- selected semantics;
- content descriptions.

Keep contrast consistent with the supplied Material 3 palette.

---

# 25. MOTION AND FEEDBACK

Use subtle state feedback only where grounded by the references.

Allowed examples:

- favorite toggle;
- button press scale/indication;
- selected chip transition;
- snackbar after add-to-cart;
- local loading/success state for Place Order;
- local loading/success state for receipt action.

Do not add elaborate page transitions, confetti, particles, parallax, or custom
animations that are not present in the design.

Prefer standard Compose/Material motion.

---

# 26. NO UNSUPPORTED BACKEND BEHAVIOR

This project is currently grounded as a UI/interaction implementation.

Do not invent:

- REST APIs;
- GraphQL;
- Firebase;
- authentication services;
- payment gateways;
- cloud cart sync;
- store inventory APIs;
- loyalty servers;
- real-time order sockets;
- GPS tracking;
- analytics;
- push notifications;
- remote image services;
- receipt servers.

If an existing Android project already contains one of these systems, preserve
it and integrate only where the current design requires.

---

# 27. NO UNNECESSARY PERMISSIONS

Do not add Android permissions solely because the visual contains an icon or
button.

In particular, do not add by default:

- camera permission;
- microphone permission;
- location permission;
- contacts permission;
- phone-call permission;
- storage permission;
- notification permission.

A voice-search icon does not establish a microphone feature.

A directions button does not establish background location tracking.

A call button does not require direct-call permission when a user-triggered
external dial intent or demo feedback is sufficient.

---

# 28. STRINGS AND COPY

Keep user-visible reference copy in Android string resources when practical.

Do not casually rewrite the supplied English product names, prices, status
labels, or CTA copy.

Do not localize product copy into another language unless the user asks for
localization.

Do not expose internal implementation terminology to the user.

---

# 29. PACKAGE AND PROJECT STRUCTURE

Do not rename the existing Android application package solely because the
`design.md` examples use `com.brewcraft.app`.

The real project package is authoritative.

Prefer a maintainable structure compatible with the existing codebase, for
example:

```text
ui/
  theme/
  home/
  customizer/
  cart/
  tracking/

data/
  model/
  repository/
navigation/
```

This folder example is not permission to perform unnecessary large-scale
refactoring.

Preserve existing conventions when they are clean and compatible with these
rules.

---

# 30. DEPENDENCY RULES

Do not add a third-party library when the feature can be implemented cleanly
with existing Android/Compose APIs.

Before adding a dependency:

1. inspect the current Gradle catalog/build files;
2. confirm the capability is truly needed;
3. prefer existing dependencies;
4. avoid adding libraries for remote images, generated icons, simple state, or
   trivial animations.

Never add a dependency merely because the Stitch HTML used a web library.

---

# 31. DEMO DATA RULES

Keep demo data deterministic.

Do not generate random:

- prices;
- ratings;
- order IDs;
- loyalty points;
- wait times;
- profile names;
- cart quantities.

Use the supplied reference values for initial state.

If a value changes because of a user action, derive it from explicit state and
rules.

Do not use timers that cause screenshots or tests to become nondeterministic
unless a real ticking timer is explicitly required.

---

# 32. SCREEN-SPECIFIC INDEPENDENCE

The Stitch references contain some demo values that are not guaranteed to be a
single continuous business transaction across all screens.

For example, the Live Tracker reference shows a different drink/order context
than the Checkout reference.

Therefore:

- do not force all hardcoded reference content into one global order object by
  assumption;
- reproduce each supplied screen accurately first;
- only connect screens dynamically when the user explicitly wants the full flow
  or the existing app architecture already establishes it;
- once connected, replace hardcoded downstream values consistently rather than
  displaying contradictory data.

---

# 33. TESTABILITY

Keep business calculations and reducers testable outside composables.

At minimum, price-related tests should cover:

- default Caramel Macchiato total `$5.95`;
- size changes;
- milk surcharge changes;
- shot-count boundaries;
- quantity boundaries;
- default Checkout total `$8.95`;
- Pickup vs Delivery fee change;
- voucher application/removal;
- item quantity changes;
- cart item removal.

UI tests may validate critical selected states and visible copy where the
project already has Compose UI testing infrastructure.

Do not introduce a large testing framework just for one screen.

---

# 34. ACCEPTANCE CHECKLIST

Before considering the BrewCraft implementation complete, verify:

## Source fidelity

- [ ] All four supplied current screen references have been inspected.
- [ ] The uploaded root `design.md` was used as the official token source.
- [ ] Actual local assets were inspected, not guessed from documentation.
- [ ] No older UiLover/e-commerce project assumptions remain.

## Assets

- [ ] No `lh3.googleusercontent.com` URLs remain.
- [ ] No `aida-public` URLs remain.
- [ ] No remote profile or product images remain.
- [ ] `profile.png` is used for Alex where appropriate.
- [ ] Barista Liam does not incorrectly reuse Alex's avatar.
- [ ] Both Caramel Macchiato asset files were inspected before assigning roles.
- [ ] Missing logo is represented natively until a real logo asset exists.

## Home

- [ ] All visible Home sections match the reference hierarchy.
- [ ] All four Popular Drinks use the correct local product asset.
- [ ] `All` is initially selected.
- [ ] Home is selected in bottom navigation.
- [ ] Cart badge initially shows `2` in untouched demo state.

## Customizer

- [ ] Default CTA price is `$5.95`.
- [ ] Medium is initially selected.
- [ ] Oat Milk is initially selected.
- [ ] 50% Standard is initially selected.
- [ ] Less Ice is initially selected.
- [ ] Hot hides Ice Level.
- [ ] Shot range is 1...4.
- [ ] Quantity range is 1...10.
- [ ] Price updates deterministically.

## Cart

- [ ] Initial subtotal is `$9.75`.
- [ ] Initial Pickup fee is `$0.50`.
- [ ] Initial promo discount is `-$2.00`.
- [ ] Initial tax is `$0.70`.
- [ ] Initial total is `$8.95`.
- [ ] The first state update does not unexpectedly change the initial total.
- [ ] Pickup/Delivery changes fees correctly.
- [ ] Payment selection is single-choice.
- [ ] No real payment API is implied.

## Tracking

- [ ] Order `#BC-8924` initial reference state is preserved when rendering the
  standalone tracking demo.
- [ ] Brewing & Crafting is the active progress step.
- [ ] QR/pass content is local.
- [ ] No remote barista/roastery image remains.
- [ ] Rewards values match the reference.
- [ ] Call/directions/receipt/help do not require unsupported services.

## Architecture

- [ ] Screen composables are stateless renderers where practical.
- [ ] UI state is immutable.
- [ ] Actions flow one way through ViewModels/reducers.
- [ ] State is collected lifecycle-aware.
- [ ] No WebView/HTML/CSS/JavaScript implementation exists.
- [ ] No unnecessary dependency or permission was added.

---

# 35. FINAL IMPLEMENTATION PRINCIPLE

When uncertain, do not improvise a new product requirement.

Use this order:

**current screen reference → uploaded design.md → real local assets → existing
matching Android code → Stitch HTML behavior**

The goal is not to make a generic coffee app.

The goal is to reproduce the supplied BrewCraft mobile UI faithfully as a
clean, native, maintainable Jetpack Compose application using only grounded
content and deterministic local behavior.
