package jaggl;

/**
 * @author Walied-Yassen
 */
public interface GLConstants {
	//@formatter:off
	/** AccumOp */
	public static final int
		GL_ACCUM  = 256,
		GL_LOAD   = 257,
		GL_RETURN = 258,
		GL_MULT   = 259,
		GL_ADD    = 260;

	/** AlphaFunction */
	public static final int
		GL_NEVER    = 512,
		GL_LESS     = 513,
		GL_EQUAL    = 514,
		GL_LEQUAL   = 515,
		GL_GREATER  = 516,
		GL_NOTEQUAL = 517,
		GL_GEQUAL   = 518,
		GL_ALWAYS   = 519;

	/** AttribMask */
	public static final int
		GL_CURRENT_BIT         = 1,
		GL_POINT_BIT           = 2,
		GL_LINE_BIT            = 4,
		GL_POLYGON_BIT         = 8,
		GL_POLYGON_STIPPLE_BIT = 16,
		GL_PIXEL_MODE_BIT      = 32,
		GL_LIGHTING_BIT        = 64,
		GL_FOG_BIT             = 128,
		GL_DEPTH_BUFFER_BIT    = 256,
		GL_ACCUM_BUFFER_BIT    = 512,
		GL_STENCIL_BUFFER_BIT  = 1024,
		GL_VIEWPORT_BIT        = 2048,
		GL_TRANSFORM_BIT       = 4096,
		GL_ENABLE_BIT          = 8192,
		GL_COLOR_BUFFER_BIT    = 16384,
		GL_HINT_BIT            = 32768,
		GL_EVAL_BIT            = 65536,
		GL_LIST_BIT            = 131072,
		GL_TEXTURE_BIT         = 262144,
		GL_SCISSOR_BIT         = 524288,
		GL_ALL_ATTRIB_BITS     = 1048575;

	/** BeginMode */
	public static final int
		GL_POINTS         = 0,
		GL_LINES          = 1,
		GL_LINE_LOOP      = 2,
		GL_LINE_STRIP     = 3,
		GL_TRIANGLES      = 4,
		GL_TRIANGLE_STRIP = 5,
		GL_TRIANGLE_FAN   = 6,
		GL_QUADS          = 7,
		GL_QUAD_STRIP     = 8,
		GL_POLYGON        = 9;

	/** BlendingFactorDest */
	public static final int
		GL_ZERO                = 0,
		GL_ONE                 = 1,
		GL_SRC_COLOR           = 768,
		GL_ONE_MINUS_SRC_COLOR = 769,
		GL_SRC_ALPHA           = 770,
		GL_ONE_MINUS_SRC_ALPHA = 771,
		GL_DST_ALPHA           = 772,
		GL_ONE_MINUS_DST_ALPHA = 773;

	/** BlendingFactorSrc */
	public static final int
		GL_DST_COLOR           = 774,
		GL_ONE_MINUS_DST_COLOR = 775,
		GL_SRC_ALPHA_SATURATE  = 776;

	/** Boolean */
	public static final int
		GL_TRUE  = 1,
		GL_FALSE = 0;

	/** ClipPlaneName */
	public static final int
		GL_CLIP_PLANE0 = 12288,
		GL_CLIP_PLANE1 = 12289,
		GL_CLIP_PLANE2 = 12290,
		GL_CLIP_PLANE3 = 12291,
		GL_CLIP_PLANE4 = 12292,
		GL_CLIP_PLANE5 = 12293;

	/** DataType */
	public static final int
		GL_BYTE           = 5120,
		GL_UNSIGNED_BYTE  = 5121,
		GL_SHORT          = 5122,
		GL_UNSIGNED_SHORT = 5123,
		GL_INT            = 5124,
		GL_UNSIGNED_INT   = 5125,
		GL_FLOAT          = 5126,
		GL_2_BYTES        = 5127,
		GL_3_BYTES        = 5128,
		GL_4_BYTES        = 5129,
		GL_DOUBLE         = 5130;

	/** DrawBufferMode */
	public static final int
		GL_NONE           = 0,
		GL_FRONT_LEFT     = 1024,
		GL_FRONT_RIGHT    = 1025,
		GL_BACK_LEFT      = 1026,
		GL_BACK_RIGHT     = 1027,
		GL_FRONT          = 1028,
		GL_BACK           = 1029,
		GL_LEFT           = 1030,
		GL_RIGHT          = 1031,
		GL_FRONT_AND_BACK = 1032,
		GL_AUX0           = 1033,
		GL_AUX1           = 1034,
		GL_AUX2           = 1035,
		GL_AUX3           = 1036;

	/** ErrorCode */
	public static final int
		GL_NO_ERROR          = 0,
		GL_INVALID_ENUM      = 1280,
		GL_INVALID_VALUE     = 1281,
		GL_INVALID_OPERATION = 1282,
		GL_STACK_OVERFLOW    = 1283,
		GL_STACK_UNDERFLOW   = 1284,
		GL_OUT_OF_MEMORY     = 1285;

	/** FeedBackMode */
	public static final int
		GL_2D               = 1536,
		GL_3D               = 1537,
		GL_3D_COLOR         = 1538,
		GL_3D_COLOR_TEXTURE = 1539,
		GL_4D_COLOR_TEXTURE = 1540;

	/** FeedBackToken */
	public static final int
		GL_PASS_THROUGH_TOKEN = 1792,
		GL_POINT_TOKEN        = 1793,
		GL_LINE_TOKEN         = 1794,
		GL_POLYGON_TOKEN      = 1795,
		GL_BITMAP_TOKEN       = 1796,
		GL_DRAW_PIXEL_TOKEN   = 1797,
		GL_COPY_PIXEL_TOKEN   = 1798,
		GL_LINE_RESET_TOKEN   = 1799;

	/** FogMode */
	public static final int
		GL_EXP  = 2048,
		GL_EXP2 = 2049;

	/** FrontFaceDirection */
	public static final int
		GL_CW  = 2304,
		GL_CCW = 2305;

	/** GetMapTarget */
	public static final int
		GL_COEFF  = 2560,
		GL_ORDER  = 2561,
		GL_DOMAIN = 2562;

	/** GetTarget */
	public static final int
		GL_CURRENT_COLOR                 = 2816,
		GL_CURRENT_INDEX                 = 2817,
		GL_CURRENT_NORMAL                = 2818,
		GL_CURRENT_TEXTURE_COORDS        = 2819,
		GL_CURRENT_RASTER_COLOR          = 2820,
		GL_CURRENT_RASTER_INDEX          = 2821,
		GL_CURRENT_RASTER_TEXTURE_COORDS = 2822,
		GL_CURRENT_RASTER_POSITION       = 2823,
		GL_CURRENT_RASTER_POSITION_VALID = 2824,
		GL_CURRENT_RASTER_DISTANCE       = 2825,
		GL_POINT_SMOOTH                  = 2832,
		GL_POINT_SIZE                    = 2833,
		GL_POINT_SIZE_RANGE              = 2834,
		GL_POINT_SIZE_GRANULARITY        = 2835,
		GL_LINE_SMOOTH                   = 2848,
		GL_LINE_WIDTH                    = 2849,
		GL_LINE_WIDTH_RANGE              = 2850,
		GL_LINE_WIDTH_GRANULARITY        = 2851,
		GL_LINE_STIPPLE                  = 2852,
		GL_LINE_STIPPLE_PATTERN          = 2853,
		GL_LINE_STIPPLE_REPEAT           = 2854,
		GL_LIST_MODE                     = 2864,
		GL_MAX_LIST_NESTING              = 2865,
		GL_LIST_BASE                     = 2866,
		GL_LIST_INDEX                    = 2867,
		GL_POLYGON_MODE                  = 2880,
		GL_POLYGON_SMOOTH                = 2881,
		GL_POLYGON_STIPPLE               = 2882,
		GL_EDGE_FLAG                     = 2883,
		GL_CULL_FACE                     = 2884,
		GL_CULL_FACE_MODE                = 2885,
		GL_FRONT_FACE                    = 2886,
		GL_LIGHTING                      = 2896,
		GL_LIGHT_MODEL_LOCAL_VIEWER      = 2897,
		GL_LIGHT_MODEL_TWO_SIDE          = 2898,
		GL_LIGHT_MODEL_AMBIENT           = 2899,
		GL_SHADE_MODEL                   = 2900,
		GL_COLOR_MATERIAL_FACE           = 2901,
		GL_COLOR_MATERIAL_PARAMETER      = 2902,
		GL_COLOR_MATERIAL                = 2903,
		GL_FOG                           = 2912,
		GL_FOG_INDEX                     = 2913,
		GL_FOG_DENSITY                   = 2914,
		GL_FOG_START                     = 2915,
		GL_FOG_END                       = 2916,
		GL_FOG_MODE                      = 2917,
		GL_FOG_COLOR                     = 2918,
		GL_DEPTH_RANGE                   = 2928,
		GL_DEPTH_TEST                    = 2929,
		GL_DEPTH_WRITEMASK               = 2930,
		GL_DEPTH_CLEAR_VALUE             = 2931,
		GL_DEPTH_FUNC                    = 2932,
		GL_ACCUM_CLEAR_VALUE             = 2944,
		GL_STENCIL_TEST                  = 2960,
		GL_STENCIL_CLEAR_VALUE           = 2961,
		GL_STENCIL_FUNC                  = 2962,
		GL_STENCIL_VALUE_MASK            = 2963,
		GL_STENCIL_FAIL                  = 2964,
		GL_STENCIL_PASS_DEPTH_FAIL       = 2965,
		GL_STENCIL_PASS_DEPTH_PASS       = 2966,
		GL_STENCIL_REF                   = 2967,
		GL_STENCIL_WRITEMASK             = 2968,
		GL_MATRIX_MODE                   = 2976,
		GL_NORMALIZE                     = 2977,
		GL_VIEWPORT                      = 2978,
		GL_MODELVIEW_STACK_DEPTH         = 2979,
		GL_PROJECTION_STACK_DEPTH        = 2980,
		GL_TEXTURE_STACK_DEPTH           = 2981,
		GL_MODELVIEW_MATRIX              = 2982,
		GL_PROJECTION_MATRIX             = 2983,
		GL_TEXTURE_MATRIX                = 2984,
		GL_ATTRIB_STACK_DEPTH            = 2992,
		GL_CLIENT_ATTRIB_STACK_DEPTH     = 2993,
		GL_ALPHA_TEST                    = 3008,
		GL_ALPHA_TEST_FUNC               = 3009,
		GL_ALPHA_TEST_REF                = 3010,
		GL_DITHER                        = 3024,
		GL_BLEND_DST                     = 3040,
		GL_BLEND_SRC                     = 3041,
		GL_BLEND                         = 3042,
		GL_LOGIC_OP_MODE                 = 3056,
		GL_INDEX_LOGIC_OP                = 3057,
		GL_LOGIC_OP                      = 3057,
		GL_COLOR_LOGIC_OP                = 3058,
		GL_AUX_BUFFERS                   = 3072,
		GL_DRAW_BUFFER                   = 3073,
		GL_READ_BUFFER                   = 3074,
		GL_SCISSOR_BOX                   = 3088,
		GL_SCISSOR_TEST                  = 3089,
		GL_INDEX_CLEAR_VALUE             = 3104,
		GL_INDEX_WRITEMASK               = 3105,
		GL_COLOR_CLEAR_VALUE             = 3106,
		GL_COLOR_WRITEMASK               = 3107,
		GL_INDEX_MODE                    = 3120,
		GL_RGBA_MODE                     = 3121,
		GL_DOUBLEBUFFER                  = 3122,
		GL_STEREO                        = 3123,
		GL_RENDER_MODE                   = 3136,
		GL_PERSPECTIVE_CORRECTION_HINT   = 3152,
		GL_POINT_SMOOTH_HINT             = 3153,
		GL_LINE_SMOOTH_HINT              = 3154,
		GL_POLYGON_SMOOTH_HINT           = 3155,
		GL_FOG_HINT                      = 3156,
		GL_TEXTURE_GEN_S                 = 3168,
		GL_TEXTURE_GEN_T                 = 3169,
		GL_TEXTURE_GEN_R                 = 3170,
		GL_TEXTURE_GEN_Q                 = 3171,
		GL_PIXEL_MAP_I_TO_I              = 3184,
		GL_PIXEL_MAP_S_TO_S              = 3185,
		GL_PIXEL_MAP_I_TO_R              = 3186,
		GL_PIXEL_MAP_I_TO_G              = 3187,
		GL_PIXEL_MAP_I_TO_B              = 3188,
		GL_PIXEL_MAP_I_TO_A              = 3189,
		GL_PIXEL_MAP_R_TO_R              = 3190,
		GL_PIXEL_MAP_G_TO_G              = 3191,
		GL_PIXEL_MAP_B_TO_B              = 3192,
		GL_PIXEL_MAP_A_TO_A              = 3193,
		GL_PIXEL_MAP_I_TO_I_SIZE         = 3248,
		GL_PIXEL_MAP_S_TO_S_SIZE         = 3249,
		GL_PIXEL_MAP_I_TO_R_SIZE         = 3250,
		GL_PIXEL_MAP_I_TO_G_SIZE         = 3251,
		GL_PIXEL_MAP_I_TO_B_SIZE         = 3252,
		GL_PIXEL_MAP_I_TO_A_SIZE         = 3253,
		GL_PIXEL_MAP_R_TO_R_SIZE         = 3254,
		GL_PIXEL_MAP_G_TO_G_SIZE         = 3255,
		GL_PIXEL_MAP_B_TO_B_SIZE         = 3256,
		GL_PIXEL_MAP_A_TO_A_SIZE         = 3257,
		GL_UNPACK_SWAP_BYTES             = 3312,
		GL_UNPACK_LSB_FIRST              = 3313,
		GL_UNPACK_ROW_LENGTH             = 3314,
		GL_UNPACK_SKIP_ROWS              = 3315,
		GL_UNPACK_SKIP_PIXELS            = 3316,
		GL_UNPACK_ALIGNMENT              = 3317,
		GL_PACK_SWAP_BYTES               = 3328,
		GL_PACK_LSB_FIRST                = 3329,
		GL_PACK_ROW_LENGTH               = 3330,
		GL_PACK_SKIP_ROWS                = 3331,
		GL_PACK_SKIP_PIXELS              = 3332,
		GL_PACK_ALIGNMENT                = 3333,
		GL_MAP_COLOR                     = 3344,
		GL_MAP_STENCIL                   = 3345,
		GL_INDEX_SHIFT                   = 3346,
		GL_INDEX_OFFSET                  = 3347,
		GL_RED_SCALE                     = 3348,
		GL_RED_BIAS                      = 3349,
		GL_ZOOM_X                        = 3350,
		GL_ZOOM_Y                        = 3351,
		GL_GREEN_SCALE                   = 3352,
		GL_GREEN_BIAS                    = 3353,
		GL_BLUE_SCALE                    = 3354,
		GL_BLUE_BIAS                     = 3355,
		GL_ALPHA_SCALE                   = 3356,
		GL_ALPHA_BIAS                    = 3357,
		GL_DEPTH_SCALE                   = 3358,
		GL_DEPTH_BIAS                    = 3359,
		GL_MAX_EVAL_ORDER                = 3376,
		GL_MAX_LIGHTS                    = 3377,
		GL_MAX_CLIP_PLANES               = 3378,
		GL_MAX_TEXTURE_SIZE              = 3379,
		GL_MAX_PIXEL_MAP_TABLE           = 3380,
		GL_MAX_ATTRIB_STACK_DEPTH        = 3381,
		GL_MAX_MODELVIEW_STACK_DEPTH     = 3382,
		GL_MAX_NAME_STACK_DEPTH          = 3383,
		GL_MAX_PROJECTION_STACK_DEPTH    = 3384,
		GL_MAX_TEXTURE_STACK_DEPTH       = 3385,
		GL_MAX_VIEWPORT_DIMS             = 3386,
		GL_MAX_CLIENT_ATTRIB_STACK_DEPTH = 3387,
		GL_SUBPIXEL_BITS                 = 3408,
		GL_INDEX_BITS                    = 3409,
		GL_RED_BITS                      = 3410,
		GL_GREEN_BITS                    = 3411,
		GL_BLUE_BITS                     = 3412,
		GL_ALPHA_BITS                    = 3413,
		GL_DEPTH_BITS                    = 3414,
		GL_STENCIL_BITS                  = 3415,
		GL_ACCUM_RED_BITS                = 3416,
		GL_ACCUM_GREEN_BITS              = 3417,
		GL_ACCUM_BLUE_BITS               = 3418,
		GL_ACCUM_ALPHA_BITS              = 3419,
		GL_NAME_STACK_DEPTH              = 3440,
		GL_AUTO_NORMAL                   = 3456,
		GL_MAP1_COLOR_4                  = 3472,
		GL_MAP1_INDEX                    = 3473,
		GL_MAP1_NORMAL                   = 3474,
		GL_MAP1_TEXTURE_COORD_1          = 3475,
		GL_MAP1_TEXTURE_COORD_2          = 3476,
		GL_MAP1_TEXTURE_COORD_3          = 3477,
		GL_MAP1_TEXTURE_COORD_4          = 3478,
		GL_MAP1_VERTEX_3                 = 3479,
		GL_MAP1_VERTEX_4                 = 3480,
		GL_MAP2_COLOR_4                  = 3504,
		GL_MAP2_INDEX                    = 3505,
		GL_MAP2_NORMAL                   = 3506,
		GL_MAP2_TEXTURE_COORD_1          = 3507,
		GL_MAP2_TEXTURE_COORD_2          = 3508,
		GL_MAP2_TEXTURE_COORD_3          = 3509,
		GL_MAP2_TEXTURE_COORD_4          = 3510,
		GL_MAP2_VERTEX_3                 = 3511,
		GL_MAP2_VERTEX_4                 = 3512,
		GL_MAP1_GRID_DOMAIN              = 3536,
		GL_MAP1_GRID_SEGMENTS            = 3537,
		GL_MAP2_GRID_DOMAIN              = 3538,
		GL_MAP2_GRID_SEGMENTS            = 3539,
		GL_TEXTURE_1D                    = 3552,
		GL_TEXTURE_2D                    = 3553,
		GL_FEEDBACK_BUFFER_POINTER       = 3568,
		GL_FEEDBACK_BUFFER_SIZE          = 3569,
		GL_FEEDBACK_BUFFER_TYPE          = 3570,
		GL_SELECTION_BUFFER_POINTER      = 3571,
		GL_SELECTION_BUFFER_SIZE         = 3572;

	/** GetTextureParameter */
	public static final int
		GL_TEXTURE_WIDTH           = 4096,
		GL_TEXTURE_HEIGHT          = 4097,
		GL_TEXTURE_INTERNAL_FORMAT = 4099,
		GL_TEXTURE_COMPONENTS      = 4099,
		GL_TEXTURE_BORDER_COLOR    = 4100,
		GL_TEXTURE_BORDER          = 4101;

	/** HintMode */
	public static final int
		GL_DONT_CARE = 4352,
		GL_FASTEST   = 4353,
		GL_NICEST    = 4354;

	/** LightName */
	public static final int
		GL_LIGHT0 = 16384,
		GL_LIGHT1 = 16385,
		GL_LIGHT2 = 16386,
		GL_LIGHT3 = 16387,
		GL_LIGHT4 = 16388,
		GL_LIGHT5 = 16389,
		GL_LIGHT6 = 16390,
		GL_LIGHT7 = 16391;

	/** LightParameter */
	public static final int
		GL_AMBIENT               = 4608,
		GL_DIFFUSE               = 4609,
		GL_SPECULAR              = 4610,
		GL_POSITION              = 4611,
		GL_SPOT_DIRECTION        = 4612,
		GL_SPOT_EXPONENT         = 4613,
		GL_SPOT_CUTOFF           = 4614,
		GL_CONSTANT_ATTENUATION  = 4615,
		GL_LINEAR_ATTENUATION    = 4616,
		GL_QUADRATIC_ATTENUATION = 4617;

	/** ListMode */
	public static final int
		GL_COMPILE             = 4864,
		GL_COMPILE_AND_EXECUTE = 4865;

	/** LogicOp */
	public static final int
		GL_CLEAR         = 5376,
		GL_AND           = 5377,
		GL_AND_REVERSE   = 5378,
		GL_COPY          = 5379,
		GL_AND_INVERTED  = 5380,
		GL_NOOP          = 5381,
		GL_XOR           = 5382,
		GL_OR            = 5383,
		GL_NOR           = 5384,
		GL_EQUIV         = 5385,
		GL_INVERT        = 5386,
		GL_OR_REVERSE    = 5387,
		GL_COPY_INVERTED = 5388,
		GL_OR_INVERTED   = 5389,
		GL_NAND          = 5390,
		GL_SET           = 5391;

	/** MaterialParameter */
	public static final int
		GL_EMISSION            = 5632,
		GL_SHININESS           = 5633,
		GL_AMBIENT_AND_DIFFUSE = 5634,
		GL_COLOR_INDEXES       = 5635;

	/** MatrixMode */
	public static final int
		GL_MODELVIEW  = 5888,
		GL_PROJECTION = 5889,
		GL_TEXTURE    = 5890;

	/** PixelCopyType */
	public static final int
		GL_COLOR   = 6144,
		GL_DEPTH   = 6145,
		GL_STENCIL = 6146;

	/** PixelFormat */
	public static final int
		GL_COLOR_INDEX     = 6400,
		GL_STENCIL_INDEX   = 6401,
		GL_DEPTH_COMPONENT = 6402,
		GL_RED             = 6403,
		GL_GREEN           = 6404,
		GL_BLUE            = 6405,
		GL_ALPHA           = 6406,
		GL_RGB             = 6407,
		GL_RGBA            = 6408,
		GL_LUMINANCE       = 6409,
		GL_LUMINANCE_ALPHA = 6410;

	/** PixelType */
	public static final int GL_BITMAP = 6656;

	/** PolygonMode */
	public static final int
		GL_POINT = 6912,
		GL_LINE  = 6913,
		GL_FILL  = 6914;

	/** RenderingMode */
	public static final int
		GL_RENDER   = 7168,
		GL_FEEDBACK = 7169,
		GL_SELECT   = 7170;

	/** ShadingModel */
	public static final int
		GL_FLAT   = 7424,
		GL_SMOOTH = 7425;

	/** StencilOp */
	public static final int
		GL_KEEP    = 7680,
		GL_REPLACE = 7681,
		GL_INCR    = 7682,
		GL_DECR    = 7683;

	/** StringName */
	public static final int
		GL_VENDOR     = 7936,
		GL_RENDERER   = 7937,
		GL_VERSION    = 7938,
		GL_EXTENSIONS = 7939;

	/** TextureCoordName */
	public static final int
		GL_S = 8192,
		GL_T = 8193,
		GL_R = 8194,
		GL_Q = 8195;

	/** TextureEnvMode */
	public static final int
		GL_MODULATE = 8448,
		GL_DECAL    = 8449;

	/** TextureEnvParameter */
	public static final int
		GL_TEXTURE_ENV_MODE  = 8704,
		GL_TEXTURE_ENV_COLOR = 8705;

	/** TextureEnvTarget */
	public static final int GL_TEXTURE_ENV = 8960;

	/** TextureGenMode */
	public static final int
		GL_EYE_LINEAR    = 9216,
		GL_OBJECT_LINEAR = 9217,
		GL_SPHERE_MAP    = 9218;

	/** TextureGenParameter */
	public static final int
		GL_TEXTURE_GEN_MODE = 9472,
		GL_OBJECT_PLANE     = 9473,
		GL_EYE_PLANE        = 9474;

	/** TextureMagFilter */
	public static final int
		GL_NEAREST = 9728,
		GL_LINEAR  = 9729;

	/** TextureMinFilter */
	public static final int
		GL_NEAREST_MIPMAP_NEAREST = 9984,
		GL_LINEAR_MIPMAP_NEAREST  = 9985,
		GL_NEAREST_MIPMAP_LINEAR  = 9986,
		GL_LINEAR_MIPMAP_LINEAR   = 9987;

	/** TextureParameterName */
	public static final int
		GL_TEXTURE_MAG_FILTER = 10240,
		GL_TEXTURE_MIN_FILTER = 10241,
		GL_TEXTURE_WRAP_S     = 10242,
		GL_TEXTURE_WRAP_T     = 10243;

	/** TextureWrapMode */
	public static final int
		GL_CLAMP  = 10496,
		GL_REPEAT = 10497;

	/** ClientAttribMask */
	public static final int
		GL_CLIENT_PIXEL_STORE_BIT  = 1,
		GL_CLIENT_VERTEX_ARRAY_BIT = 2,
		GL_CLIENT_ALL_ATTRIB_BITS  = -1;

	/** polygon_offset */
	public static final int
		GL_POLYGON_OFFSET_FACTOR = 32824,
		GL_POLYGON_OFFSET_UNITS  = 10752,
		GL_POLYGON_OFFSET_POINT  = 10753,
		GL_POLYGON_OFFSET_LINE   = 10754,
		GL_POLYGON_OFFSET_FILL   = 32823;

	/** texture */
	public static final int
		GL_ALPHA4                 = 32827,
		GL_ALPHA8                 = 32828,
		GL_ALPHA12                = 32829,
		GL_ALPHA16                = 32830,
		GL_LUMINANCE4             = 32831,
		GL_LUMINANCE8             = 32832,
		GL_LUMINANCE12            = 32833,
		GL_LUMINANCE16            = 32834,
		GL_LUMINANCE4_ALPHA4      = 32835,
		GL_LUMINANCE6_ALPHA2      = 32836,
		GL_LUMINANCE8_ALPHA8      = 32837,
		GL_LUMINANCE12_ALPHA4     = 32838,
		GL_LUMINANCE12_ALPHA12    = 32839,
		GL_LUMINANCE16_ALPHA16    = 32840,
		GL_INTENSITY              = 32841,
		GL_INTENSITY4             = 32842,
		GL_INTENSITY8             = 32843,
		GL_INTENSITY12            = 32844,
		GL_INTENSITY16            = 32845,
		GL_R3_G3_B2               = 10768,
		GL_RGB4                   = 32847,
		GL_RGB5                   = 32848,
		GL_RGB8                   = 32849,
		GL_RGB10                  = 32850,
		GL_RGB12                  = 32851,
		GL_RGB16                  = 32852,
		GL_RGBA2                  = 32853,
		GL_RGBA4                  = 32854,
		GL_RGB5_A1                = 32855,
		GL_RGBA8                  = 32856,
		GL_RGB10_A2               = 32857,
		GL_RGBA12                 = 32858,
		GL_RGBA16                 = 32859,
		GL_TEXTURE_RED_SIZE       = 32860,
		GL_TEXTURE_GREEN_SIZE     = 32861,
		GL_TEXTURE_BLUE_SIZE      = 32862,
		GL_TEXTURE_ALPHA_SIZE     = 32863,
		GL_TEXTURE_LUMINANCE_SIZE = 32864,
		GL_TEXTURE_INTENSITY_SIZE = 32865,
		GL_PROXY_TEXTURE_1D       = 32867,
		GL_PROXY_TEXTURE_2D       = 32868;

	/** texture_object */
	public static final int
		GL_TEXTURE_PRIORITY   = 32870,
		GL_TEXTURE_RESIDENT   = 32871,
		GL_TEXTURE_BINDING_1D = 32872,
		GL_TEXTURE_BINDING_2D = 32873;

	/** vertex_array */
	public static final int
		GL_VERTEX_ARRAY                = 32884,
		GL_NORMAL_ARRAY                = 32885,
		GL_COLOR_ARRAY                 = 32886,
		GL_INDEX_ARRAY                 = 32887,
		GL_TEXTURE_COORD_ARRAY         = 32888,
		GL_EDGE_FLAG_ARRAY             = 32889,
		GL_VERTEX_ARRAY_SIZE           = 32890,
		GL_VERTEX_ARRAY_TYPE           = 32891,
		GL_VERTEX_ARRAY_STRIDE         = 32892,
		GL_NORMAL_ARRAY_TYPE           = 32894,
		GL_NORMAL_ARRAY_STRIDE         = 32895,
		GL_COLOR_ARRAY_SIZE            = 32897,
		GL_COLOR_ARRAY_TYPE            = 32898,
		GL_COLOR_ARRAY_STRIDE          = 32899,
		GL_INDEX_ARRAY_TYPE            = 32901,
		GL_INDEX_ARRAY_STRIDE          = 32902,
		GL_TEXTURE_COORD_ARRAY_SIZE    = 32904,
		GL_TEXTURE_COORD_ARRAY_TYPE    = 32905,
		GL_TEXTURE_COORD_ARRAY_STRIDE  = 32906,
		GL_EDGE_FLAG_ARRAY_STRIDE      = 32908,
		GL_VERTEX_ARRAY_POINTER        = 32910,
		GL_NORMAL_ARRAY_POINTER        = 32911,
		GL_COLOR_ARRAY_POINTER         = 32912,
		GL_INDEX_ARRAY_POINTER         = 32913,
		GL_TEXTURE_COORD_ARRAY_POINTER = 32914,
		GL_EDGE_FLAG_ARRAY_POINTER     = 32915,
		GL_V2F                         = 10784,
		GL_V3F                         = 10785,
		GL_C4UB_V2F                    = 10786,
		GL_C4UB_V3F                    = 10787,
		GL_C3F_V3F                     = 10788,
		GL_N3F_V3F                     = 10789,
		GL_C4F_N3F_V3F                 = 10790,
		GL_T2F_V3F                     = 10791,
		GL_T4F_V4F                     = 10792,
		GL_T2F_C4UB_V3F                = 10793,
		GL_T2F_C3F_V3F                 = 10794,
		GL_T2F_N3F_V3F                 = 10795,
		GL_T2F_C4F_N3F_V3F             = 10796,
		GL_T4F_C4F_N3F_V4F             = 10797;
	
	/** Aliases for smooth points and lines. */
	public static final int
		GL_ALIASED_POINT_SIZE_RANGE      = 33901,
		GL_ALIASED_LINE_WIDTH_RANGE      = 33902,
		GL_SMOOTH_POINT_SIZE_RANGE       = 2834,
		GL_SMOOTH_POINT_SIZE_GRANULARITY = 2835,
		GL_SMOOTH_LINE_WIDTH_RANGE       = 2850,
		GL_SMOOTH_LINE_WIDTH_GRANULARITY = 2851;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_TEXTURE_BINDING_3D = 32874;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev, and by the {@code pname} parameter of PixelStore. */
	public static final int
		GL_PACK_SKIP_IMAGES    = 32875,
		GL_PACK_IMAGE_HEIGHT   = 32876,
		GL_UNPACK_SKIP_IMAGES  = 32877,
		GL_UNPACK_IMAGE_HEIGHT = 32878;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev, and by the {@code target} parameter of TexImage3D, GetTexImage, GetTexLevelParameteriv, GetTexLevelParameterfv, GetTexParameteriv, and
	 * GetTexParameterfv.
	 */
	public static final int GL_TEXTURE_3D = 32879;

	/** Accepted by the {@code target} parameter of TexImage3D, GetTexLevelParameteriv, and GetTexLevelParameterfv. */
	public static final int GL_PROXY_TEXTURE_3D = 32880;

	/** Accepted by the {@code pname} parameter of GetTexLevelParameteriv and GetTexLevelParameterfv. */
	public static final int GL_TEXTURE_DEPTH = 32881;

	/** Accepted by the {@code pname} parameter of TexParameteriv, TexParameterfv, GetTexParameteriv, and GetTexParameterfv. */
	public static final int GL_TEXTURE_WRAP_R = 32882;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_MAX_3D_TEXTURE_SIZE = 32883;

	/** Accepted by the {@code format} parameter of DrawPixels, GetTexImage, ReadPixels, TexImage1D, and TexImage2D. */
	public static final int
		GL_BGR  = 32992,
		GL_BGRA = 32993;

	/**
	 * Accepted by the {@code type} parameter of DrawPixels, ReadPixels, TexImage1D, TexImage2D, GetTexImage, TexImage3D, TexSubImage1D, TexSubImage2D,
	 * TexSubImage3D, GetHistogram, GetMinmax, ConvolutionFilter1D, ConvolutionFilter2D, ConvolutionFilter3D, GetConvolutionFilter, SeparableFilter2D,
	 * SeparableFilter3D, GetSeparableFilter, ColorTable, GetColorTable, TexImage4D, and TexSubImage4D.
	 */
	public static final int
		GL_UNSIGNED_BYTE_3_3_2         = 32818,
		GL_UNSIGNED_BYTE_2_3_3_REV     = 33634,
		GL_UNSIGNED_SHORT_5_6_5        = 33635,
		GL_UNSIGNED_SHORT_5_6_5_REV    = 33636,
		GL_UNSIGNED_SHORT_4_4_4_4      = 32819,
		GL_UNSIGNED_SHORT_4_4_4_4_REV  = 33637,
		GL_UNSIGNED_SHORT_5_5_5_1      = 32820,
		GL_UNSIGNED_SHORT_1_5_5_5_REV  = 33638,
		GL_UNSIGNED_INT_8_8_8_8        = 32821,
		GL_UNSIGNED_INT_8_8_8_8_REV    = 33639,
		GL_UNSIGNED_INT_10_10_10_2     = 32822,
		GL_UNSIGNED_INT_2_10_10_10_REV = 33640;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev.
	 */
	public static final int GL_RESCALE_NORMAL = 32826;

	/** Accepted by the {@code pname} parameter of LightModel*, and also by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_LIGHT_MODEL_COLOR_CONTROL = 33272;

	/** Accepted by the {@code param} parameter of LightModel* when {@code pname} is  LIGHT_MODEL_COLOR_CONTROL. */
	public static final int
		GL_SINGLE_COLOR            = 33273,
		GL_SEPARATE_SPECULAR_COLOR = 33274;

	/**
	 * Accepted by the {@code param} parameter of TexParameteri and TexParameterf, and by the {@code params} parameter of TexParameteriv and TexParameterfv,
	 * when their {@code pname} parameter is TEXTURE_WRAP_S, TEXTURE_WRAP_T, or TEXTURE_WRAP_R.
	 */
	public static final int GL_CLAMP_TO_EDGE = 33071;

	/** Accepted by the {@code pname} parameter of TexParameteri, TexParameterf, TexParameteriv, TexParameterfv, GetTexParameteriv, and GetTexParameterfv. */
	public static final int
		GL_TEXTURE_MIN_LOD    = 33082,
		GL_TEXTURE_MAX_LOD    = 33083,
		GL_TEXTURE_BASE_LEVEL = 33084,
		GL_TEXTURE_MAX_LEVEL  = 33085;

	/** Recommended maximum amounts of vertex and index data. */
	public static final int
		GL_MAX_ELEMENTS_VERTICES = 33000,
		GL_MAX_ELEMENTS_INDICES  = 33001;
	
	/** Accepted by the {@code internalformat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D, and CopyTexImage2D. */
	public static final int
		GL_COMPRESSED_ALPHA           = 34025,
		GL_COMPRESSED_LUMINANCE       = 34026,
		GL_COMPRESSED_LUMINANCE_ALPHA = 34027,
		GL_COMPRESSED_INTENSITY       = 34028,
		GL_COMPRESSED_RGB             = 34029,
		GL_COMPRESSED_RGBA            = 34030;

	/** Accepted by the {@code target} parameter of Hint and the {@code value} parameter of GetIntegerv, GetBooleanv, GetFloatv, and GetDoublev. */
	public static final int GL_TEXTURE_COMPRESSION_HINT = 34031;

	/** Accepted by the {@code value} parameter of GetTexLevelParameter. */
	public static final int
		GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 34464,
		GL_TEXTURE_COMPRESSED            = 34465;

	/** Accepted by the {@code value} parameter of GetIntegerv, GetBooleanv, GetFloatv, and GetDoublev. */
	public static final int
		GL_NUM_COMPRESSED_TEXTURE_FORMATS = 34466,
		GL_COMPRESSED_TEXTURE_FORMATS     = 34467;

	/** Accepted by the {@code param} parameters of TexGend, TexGenf, and TexGeni when {@code pname} parameter is TEXTURE_GEN_MODE. */
	public static final int
		GL_NORMAL_MAP     = 34065,
		GL_REFLECTION_MAP = 34066;

	/**
	 * When the {@code pname} parameter of TexGendv, TexGenfv, and TexGeniv is TEXTURE_GEN_MODE, then the array {@code params} may also contain NORMAL_MAP
	 * or REFLECTION_MAP. Accepted by the {@code cap} parameter of Enable, Disable, IsEnabled, and by the {@code pname} parameter of GetBooleanv,
	 * GetIntegerv, GetFloatv, and GetDoublev, and by the {@code target} parameter of BindTexture, GetTexParameterfv, GetTexParameteriv, TexParameterf,
	 * TexParameteri, TexParameterfv, and TexParameteriv.
	 */
	public static final int GL_TEXTURE_CUBE_MAP = 34067;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;

	/**
	 * Accepted by the {@code target} parameter of GetTexImage, GetTexLevelParameteriv, GetTexLevelParameterfv, TexImage2D, CopyTexImage2D, TexSubImage2D, and
	 * CopySubTexImage2D.
	 */
	public static final int
		GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069,
		GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070,
		GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071,
		GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072,
		GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073,
		GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;

	/** Accepted by the {@code target} parameter of GetTexLevelParameteriv, GetTexLevelParameterfv, GetTexParameteriv, and TexImage2D. */
	public static final int GL_PROXY_TEXTURE_CUBE_MAP = 34075;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv. */
	public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev.
	 */
	public static final int
		GL_MULTISAMPLE              = 32925,
		GL_SAMPLE_ALPHA_TO_COVERAGE = 32926,
		GL_SAMPLE_ALPHA_TO_ONE      = 32927,
		GL_SAMPLE_COVERAGE          = 32928;

	/** Accepted by the {@code details} parameter of PushAttrib. */
	public static final int GL_MULTISAMPLE_BIT = 536870912;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv. */
	public static final int
		GL_SAMPLE_BUFFERS         = 32936,
		GL_SAMPLES                = 32937,
		GL_SAMPLE_COVERAGE_VALUE  = 32938,
		GL_SAMPLE_COVERAGE_INVERT = 32939;

	/** Accepted by the {@code texture} parameter of ActiveTexture and MultiTexCoord. */
	public static final int
		GL_TEXTURE0  = 33984,
		GL_TEXTURE1  = 33985,
		GL_TEXTURE2  = 33986,
		GL_TEXTURE3  = 33987,
		GL_TEXTURE4  = 33988,
		GL_TEXTURE5  = 33989,
		GL_TEXTURE6  = 33990,
		GL_TEXTURE7  = 33991,
		GL_TEXTURE8  = 33992,
		GL_TEXTURE9  = 33993,
		GL_TEXTURE10 = 33994,
		GL_TEXTURE11 = 33995,
		GL_TEXTURE12 = 33996,
		GL_TEXTURE13 = 33997,
		GL_TEXTURE14 = 33998,
		GL_TEXTURE15 = 33999,
		GL_TEXTURE16 = 34000,
		GL_TEXTURE17 = 34001,
		GL_TEXTURE18 = 34002,
		GL_TEXTURE19 = 34003,
		GL_TEXTURE20 = 34004,
		GL_TEXTURE21 = 34005,
		GL_TEXTURE22 = 34006,
		GL_TEXTURE23 = 34007,
		GL_TEXTURE24 = 34008,
		GL_TEXTURE25 = 34009,
		GL_TEXTURE26 = 34010,
		GL_TEXTURE27 = 34011,
		GL_TEXTURE28 = 34012,
		GL_TEXTURE29 = 34013,
		GL_TEXTURE30 = 34014,
		GL_TEXTURE31 = 34015;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv. */
	public static final int
		GL_ACTIVE_TEXTURE        = 34016,
		GL_CLIENT_ACTIVE_TEXTURE = 34017,
		GL_MAX_TEXTURE_UNITS     = 34018;

	/** Accepted by the {@code params} parameter of TexEnvf, TexEnvi, TexEnvfv, and TexEnviv when the {@code pname} parameter value is TEXTURE_ENV_MODE. */
	public static final int GL_COMBINE = 34160;

	/** Accepted by the {@code pname} parameter of TexEnvf, TexEnvi, TexEnvfv, and TexEnviv when the {@code target} parameter value is TEXTURE_ENV. */
	public static final int
		GL_COMBINE_RGB    = 34161,
		GL_COMBINE_ALPHA  = 34162,
		GL_SOURCE0_RGB    = 34176,
		GL_SOURCE1_RGB    = 34177,
		GL_SOURCE2_RGB    = 34178,
		GL_SOURCE0_ALPHA  = 34184,
		GL_SOURCE1_ALPHA  = 34185,
		GL_SOURCE2_ALPHA  = 34186,
		GL_OPERAND0_RGB   = 34192,
		GL_OPERAND1_RGB   = 34193,
		GL_OPERAND2_RGB   = 34194,
		GL_OPERAND0_ALPHA = 34200,
		GL_OPERAND1_ALPHA = 34201,
		GL_OPERAND2_ALPHA = 34202,
		GL_RGB_SCALE      = 34163;

	/**
	 * Accepted by the {@code params} parameter of TexEnvf, TexEnvi, TexEnvfv, and TexEnviv when the {@code pname} parameter value is COMBINE_RGB or
	 * COMBINE_ALPHA.
	 */
	public static final int
		GL_ADD_SIGNED  = 34164,
		GL_INTERPOLATE = 34165,
		GL_SUBTRACT    = 34023;

	/**
	 * Accepted by the {@code params} parameter of TexEnvf, TexEnvi, TexEnvfv, and TexEnviv when the {@code pname} parameter value is SOURCE0_RGB,
	 * SOURCE1_RGB, SOURCE2_RGB, SOURCE0_ALPHA, SOURCE1_ALPHA, or SOURCE2_ALPHA.
	 */
	public static final int
		GL_CONSTANT      = 34166,
		GL_PRIMARY_COLOR = 34167,
		GL_PREVIOUS      = 34168;

	/** Accepted by the {@code params} parameter of TexEnvf, TexEnvi, TexEnvfv, and TexEnviv when the {@code pname} parameter value is COMBINE_RGB_ARB. */
	public static final int
		GL_DOT3_RGB  = 34478,
		GL_DOT3_RGBA = 34479;

	/**
	 * Accepted by the {@code param} parameter of TexParameteri and TexParameterf, and by the {@code params} parameter of TexParameteriv and TexParameterfv,
	 * when their {@code pname} parameter is TEXTURE_WRAP_S, TEXTURE_WRAP_T, or TEXTURE_WRAP_R.
	 */
	public static final int GL_CLAMP_TO_BORDER = 33069;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_TRANSPOSE_MODELVIEW_MATRIX  = 34019,
		GL_TRANSPOSE_PROJECTION_MATRIX = 34020,
		GL_TRANSPOSE_TEXTURE_MATRIX    = 34021,
		GL_TRANSPOSE_COLOR_MATRIX      = 34022;
	
	/** Accepted by the {@code pname} parameter of TexParameteri, TexParameterf, TexParameteriv, TexParameterfv, GetTexParameteriv, and GetTexParameterfv. */
	public static final int GL_GENERATE_MIPMAP = 33169;

	/** Accepted by the {@code target} parameter of Hint, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_GENERATE_MIPMAP_HINT = 33170;

	/** Accepted by the {@code sfactor} and {@code dfactor} parameters of BlendFunc. */
	public static final int
		GL_CONSTANT_COLOR           = 32769,
		GL_ONE_MINUS_CONSTANT_COLOR = 32770,
		GL_CONSTANT_ALPHA           = 32771,
		GL_ONE_MINUS_CONSTANT_ALPHA = 32772;

	/** Accepted by the {@code mode} parameter of BlendEquation. */
	public static final int
		GL_FUNC_ADD = 32774,
		GL_MIN      = 32775,
		GL_MAX      = 32776;

	/** Accepted by the {@code mode} parameter of BlendEquation. */
	public static final int
		GL_FUNC_SUBTRACT         = 32778,
		GL_FUNC_REVERSE_SUBTRACT = 32779;

	/** Accepted by the {@code internalFormat} parameter of TexImage1D, TexImage2D, CopyTexImage1D and CopyTexImage2D. */
	public static final int
		GL_DEPTH_COMPONENT16 = 33189,
		GL_DEPTH_COMPONENT24 = 33190,
		GL_DEPTH_COMPONENT32 = 33191;

	/** Accepted by the {@code pname} parameter of GetTexLevelParameterfv and GetTexLevelParameteriv. */
	public static final int GL_TEXTURE_DEPTH_SIZE = 34890;

	/** Accepted by the {@code pname} parameter of TexParameterf, TexParameteri, TexParameterfv, TexParameteriv, GetTexParameterfv, and GetTexParameteriv. */
	public static final int GL_DEPTH_TEXTURE_MODE = 34891;

	/** Accepted by the {@code pname} parameter of TexParameterf, TexParameteri, TexParameterfv, TexParameteriv, GetTexParameterfv, and GetTexParameteriv. */
	public static final int
		GL_TEXTURE_COMPARE_MODE = 34892,
		GL_TEXTURE_COMPARE_FUNC = 34893;

	/**
	 * Accepted by the {@code param} parameter of TexParameterf, TexParameteri, TexParameterfv, and TexParameteriv when the {@code pname} parameter is
	 * TEXTURE_COMPARE_MODE.
	 */
	public static final int GL_COMPARE_R_TO_TEXTURE = 34894;

	/** Accepted by the {@code pname} parameter of Fogi and Fogf. */
	public static final int GL_FOG_COORDINATE_SOURCE = 33872;

	/** Accepted by the {@code param} parameter of Fogi and Fogf. */
	public static final int
		GL_FOG_COORDINATE = 33873,
		GL_FRAGMENT_DEPTH = 33874;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_CURRENT_FOG_COORDINATE      = 33875,
		GL_FOG_COORDINATE_ARRAY_TYPE   = 33876,
		GL_FOG_COORDINATE_ARRAY_STRIDE = 33877;

	/** Accepted by the {@code pname} parameter of GetPointerv. */
	public static final int GL_FOG_COORDINATE_ARRAY_POINTER = 33878;

	/** Accepted by the {@code array} parameter of EnableClientState and DisableClientState. */
	public static final int GL_FOG_COORDINATE_ARRAY = 33879;

	/** Accepted by the {@code pname} parameter of PointParameterfARB, and the {@code pname} of Get. */
	public static final int
		GL_POINT_SIZE_MIN             = 33062,
		GL_POINT_SIZE_MAX             = 33063,
		GL_POINT_FADE_THRESHOLD_SIZE  = 33064,
		GL_POINT_DISTANCE_ATTENUATION = 33065;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev.
	 */
	public static final int GL_COLOR_SUM = 33880;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_CURRENT_SECONDARY_COLOR      = 33881,
		GL_SECONDARY_COLOR_ARRAY_SIZE   = 33882,
		GL_SECONDARY_COLOR_ARRAY_TYPE   = 33883,
		GL_SECONDARY_COLOR_ARRAY_STRIDE = 33884;

	/** Accepted by the {@code pname} parameter of GetPointerv. */
	public static final int GL_SECONDARY_COLOR_ARRAY_POINTER = 33885;

	/** Accepted by the {@code array} parameter of EnableClientState and DisableClientState. */
	public static final int GL_SECONDARY_COLOR_ARRAY = 33886;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_BLEND_DST_RGB   = 32968,
		GL_BLEND_SRC_RGB   = 32969,
		GL_BLEND_DST_ALPHA = 32970,
		GL_BLEND_SRC_ALPHA = 32971;

	/** Accepted by the {@code sfail}, {@code dpfail}, and {@code dppass} parameter of StencilOp. */
	public static final int
		GL_INCR_WRAP = 34055,
		GL_DECR_WRAP = 34056;

	/** Accepted by the {@code target} parameters of GetTexEnvfv, GetTexEnviv, TexEnvi, TexEnvf, Texenviv, and TexEnvfv. */
	public static final int GL_TEXTURE_FILTER_CONTROL = 34048;

	/**
	 * When the {@code target} parameter of GetTexEnvfv, GetTexEnviv, TexEnvi, TexEnvf, TexEnviv, and TexEnvfv is TEXTURE_FILTER_CONTROL, then the value of
	 * {@code pname} may be.
	 */
	public static final int GL_TEXTURE_LOD_BIAS = 34049;

	/** Accepted by the {@code pname} parameters of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_MAX_TEXTURE_LOD_BIAS = 34045;

	/**
	 * Accepted by the {@code param} parameter of TexParameteri and TexParameterf, and by the {@code params} parameter of TexParameteriv and TexParameterfv,
	 * when their {@code pname} parameter is TEXTURE_WRAP_S, TEXTURE_WRAP_T, or TEXTURE_WRAP_R.
	 */
	public static final int GL_MIRRORED_REPEAT = 33648;
	/** New token names. */
	public static final int
		GL_FOG_COORD_SRC                  = 33872,
		GL_FOG_COORD                      = 33873,
		GL_CURRENT_FOG_COORD              = 33875,
		GL_FOG_COORD_ARRAY_TYPE           = 33876,
		GL_FOG_COORD_ARRAY_STRIDE         = 33877,
		GL_FOG_COORD_ARRAY_POINTER        = 33878,
		GL_FOG_COORD_ARRAY                = 33879,
		GL_FOG_COORD_ARRAY_BUFFER_BINDING = 34973,
		GL_SRC0_RGB                       = 34176,
		GL_SRC1_RGB                       = 34177,
		GL_SRC2_RGB                       = 34178,
		GL_SRC0_ALPHA                     = 34184,
		GL_SRC1_ALPHA                     = 34185,
		GL_SRC2_ALPHA                     = 34186;

	/**
	 * Accepted by the {@code target} parameters of BindBuffer, BufferData, BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData,
	 * GetBufferParameteriv, and GetBufferPointerv.
	 */
	public static final int
		GL_ARRAY_BUFFER         = 34962,
		GL_ELEMENT_ARRAY_BUFFER = 34963;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_ARRAY_BUFFER_BINDING                 = 34964,
		GL_ELEMENT_ARRAY_BUFFER_BINDING         = 34965,
		GL_VERTEX_ARRAY_BUFFER_BINDING          = 34966,
		GL_NORMAL_ARRAY_BUFFER_BINDING          = 34967,
		GL_COLOR_ARRAY_BUFFER_BINDING           = 34968,
		GL_INDEX_ARRAY_BUFFER_BINDING           = 34969,
		GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING   = 34970,
		GL_EDGE_FLAG_ARRAY_BUFFER_BINDING       = 34971,
		GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING = 34972,
		GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING  = 34973,
		GL_WEIGHT_ARRAY_BUFFER_BINDING          = 34974;

	/** Accepted by the {@code pname} parameter of GetVertexAttribiv. */
	public static final int GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING = 34975;

	/** Accepted by the {@code usage} parameter of BufferData. */
	public static final int
		GL_STREAM_DRAW  = 35040,
		GL_STREAM_READ  = 35041,
		GL_STREAM_COPY  = 35042,
		GL_STATIC_DRAW  = 35044,
		GL_STATIC_READ  = 35045,
		GL_STATIC_COPY  = 35046,
		GL_DYNAMIC_DRAW = 35048,
		GL_DYNAMIC_READ = 35049,
		GL_DYNAMIC_COPY = 35050;

	/** Accepted by the {@code access} parameter of MapBuffer. */
	public static final int
		GL_READ_ONLY  = 35000,
		GL_WRITE_ONLY = 35001,
		GL_READ_WRITE = 35002;

	/** Accepted by the {@code pname} parameter of GetBufferParameteriv. */
	public static final int
		GL_BUFFER_SIZE   = 34660,
		GL_BUFFER_USAGE  = 34661,
		GL_BUFFER_ACCESS = 35003,
		GL_BUFFER_MAPPED = 35004;

	/** Accepted by the {@code pname} parameter of GetBufferPointerv. */
	public static final int GL_BUFFER_MAP_POINTER = 35005;

	/** Accepted by the {@code target} parameter of BeginQuery, EndQuery, and GetQueryiv. */
	public static final int GL_SAMPLES_PASSED = 35092;

	/** Accepted by the {@code pname} parameter of GetQueryiv. */
	public static final int
		GL_QUERY_COUNTER_BITS = 34916,
		GL_CURRENT_QUERY      = 34917;

	/** Accepted by the {@code pname} parameter of GetQueryObjectiv and GetQueryObjectuiv. */
	public static final int
		GL_QUERY_RESULT           = 34918,
		GL_QUERY_RESULT_AVAILABLE = 34919;

	/** Accepted by the {@code name} parameter of GetString. */
	public static final int GL_SHADING_LANGUAGE_VERSION = 35724;

	/** Accepted by the {@code pname} parameter of GetInteger. */
	public static final int GL_CURRENT_PROGRAM = 35725;

	/** Accepted by the {@code pname} parameter of GetShaderiv. */
	public static final int
		GL_SHADER_TYPE                 = 35663,
		GL_DELETE_STATUS               = 35712,
		GL_COMPILE_STATUS              = 35713,
		GL_LINK_STATUS                 = 35714,
		GL_VALIDATE_STATUS             = 35715,
		GL_INFO_LOG_LENGTH             = 35716,
		GL_ATTACHED_SHADERS            = 35717,
		GL_ACTIVE_UNIFORMS             = 35718,
		GL_ACTIVE_UNIFORM_MAX_LENGTH   = 35719,
		GL_ACTIVE_ATTRIBUTES           = 35721,
		GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 35722,
		GL_SHADER_SOURCE_LENGTH        = 35720;

	/** Returned by the {@code type} parameter of GetActiveUniform. */
	public static final int
		GL_FLOAT_VEC2        = 35664,
		GL_FLOAT_VEC3        = 35665,
		GL_FLOAT_VEC4        = 35666,
		GL_INT_VEC2          = 35667,
		GL_INT_VEC3          = 35668,
		GL_INT_VEC4          = 35669,
		GL_BOOL              = 35670,
		GL_BOOL_VEC2         = 35671,
		GL_BOOL_VEC3         = 35672,
		GL_BOOL_VEC4         = 35673,
		GL_FLOAT_MAT2        = 35674,
		GL_FLOAT_MAT3        = 35675,
		GL_FLOAT_MAT4        = 35676,
		GL_SAMPLER_1D        = 35677,
		GL_SAMPLER_2D        = 35678,
		GL_SAMPLER_3D        = 35679,
		GL_SAMPLER_CUBE      = 35680,
		GL_SAMPLER_1D_SHADOW = 35681,
		GL_SAMPLER_2D_SHADOW = 35682;

	/** Accepted by the {@code type} argument of CreateShader and returned by the {@code params} parameter of GetShaderiv. */
	public static final int GL_VERTEX_SHADER = 35633;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_MAX_VERTEX_UNIFORM_COMPONENTS    = 35658,
		GL_MAX_VARYING_FLOATS               = 35659,
		GL_MAX_VERTEX_ATTRIBS               = 34921,
		GL_MAX_TEXTURE_IMAGE_UNITS          = 34930,
		GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS   = 35660,
		GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 35661,
		GL_MAX_TEXTURE_COORDS               = 34929;

	/**
	 * Accepted by the {@code cap} parameter of Disable, Enable, and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev.
	 */
	public static final int
		GL_VERTEX_PROGRAM_POINT_SIZE = 34370,
		GL_VERTEX_PROGRAM_TWO_SIDE   = 34371;

	/** Accepted by the {@code pname} parameter of GetVertexAttrib{dfi}v. */
	public static final int
		GL_VERTEX_ATTRIB_ARRAY_ENABLED    = 34338,
		GL_VERTEX_ATTRIB_ARRAY_SIZE       = 34339,
		GL_VERTEX_ATTRIB_ARRAY_STRIDE     = 34340,
		GL_VERTEX_ATTRIB_ARRAY_TYPE       = 34341,
		GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 34922,
		GL_CURRENT_VERTEX_ATTRIB          = 34342;

	/** Accepted by the {@code pname} parameter of GetVertexAttribPointerv. */
	public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 34373;

	/** Accepted by the {@code type} argument of CreateShader and returned by the {@code params} parameter of GetShaderiv. */
	public static final int GL_FRAGMENT_SHADER = 35632;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 35657;

	/** Accepted by the {@code target} parameter of Hint and the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 35723;

	/** Accepted by the {@code pname} parameters of GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_MAX_DRAW_BUFFERS = 34852,
		GL_DRAW_BUFFER0     = 34853,
		GL_DRAW_BUFFER1     = 34854,
		GL_DRAW_BUFFER2     = 34855,
		GL_DRAW_BUFFER3     = 34856,
		GL_DRAW_BUFFER4     = 34857,
		GL_DRAW_BUFFER5     = 34858,
		GL_DRAW_BUFFER6     = 34859,
		GL_DRAW_BUFFER7     = 34860,
		GL_DRAW_BUFFER8     = 34861,
		GL_DRAW_BUFFER9     = 34862,
		GL_DRAW_BUFFER10    = 34863,
		GL_DRAW_BUFFER11    = 34864,
		GL_DRAW_BUFFER12    = 34865,
		GL_DRAW_BUFFER13    = 34866,
		GL_DRAW_BUFFER14    = 34867,
		GL_DRAW_BUFFER15    = 34868;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev, and by the {@code target} parameter of TexEnvi, TexEnviv, TexEnvf, TexEnvfv, GetTexEnviv, and GetTexEnvfv.
	 */
	public static final int GL_POINT_SPRITE = 34913;

	/**
	 * When the {@code target} parameter of TexEnvf, TexEnvfv, TexEnvi, TexEnviv, GetTexEnvfv, or GetTexEnviv is POINT_SPRITE, then the value of
	 * {@code pname} may be.
	 */
	public static final int GL_COORD_REPLACE = 34914;

	/** Accepted by the {@code pname} parameter of PointParameter{if}v. */
	public static final int GL_POINT_SPRITE_COORD_ORIGIN = 36000;

	/** Accepted by the {@code param} parameter of PointParameter{if}v. */
	public static final int
		GL_LOWER_LEFT = 36001,
		GL_UPPER_LEFT = 36002;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_BLEND_EQUATION_RGB   = 32777,
		GL_BLEND_EQUATION_ALPHA = 34877;

	/** Accepted by the {@code pname} parameter of GetIntegerv. */
	public static final int
		GL_STENCIL_BACK_FUNC            = 34816,
		GL_STENCIL_BACK_FAIL            = 34817,
		GL_STENCIL_BACK_PASS_DEPTH_FAIL = 34818,
		GL_STENCIL_BACK_PASS_DEPTH_PASS = 34819,
		GL_STENCIL_BACK_REF             = 36003,
		GL_STENCIL_BACK_VALUE_MASK      = 36004,
		GL_STENCIL_BACK_WRITEMASK       = 36005;
	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_CURRENT_RASTER_SECONDARY_COLOR = 33887;

	/** Returned by the {@code type} parameter of GetActiveUniform. */
	public static final int
		GL_FLOAT_MAT2x3 = 35685,
		GL_FLOAT_MAT2x4 = 35686,
		GL_FLOAT_MAT3x2 = 35687,
		GL_FLOAT_MAT3x4 = 35688,
		GL_FLOAT_MAT4x2 = 35689,
		GL_FLOAT_MAT4x3 = 35690;

	/**
	 * Accepted by the {@code target} parameters of BindBuffer, BufferData, BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, GetBufferParameteriv, and
	 * GetBufferPointerv.
	 */
	public static final int
		GL_PIXEL_PACK_BUFFER   = 35051,
		GL_PIXEL_UNPACK_BUFFER = 35052;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_PIXEL_PACK_BUFFER_BINDING   = 35053,
		GL_PIXEL_UNPACK_BUFFER_BINDING = 35055;

	/** Accepted by the {@code internalformat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D. */
	public static final int
		GL_SRGB                        = 35904,
		GL_SRGB8                       = 35905,
		GL_SRGB_ALPHA                  = 35906,
		GL_SRGB8_ALPHA8                = 35907,
		GL_SLUMINANCE_ALPHA            = 35908,
		GL_SLUMINANCE8_ALPHA8          = 35909,
		GL_SLUMINANCE                  = 35910,
		GL_SLUMINANCE8                 = 35911,
		GL_COMPRESSED_SRGB             = 35912,
		GL_COMPRESSED_SRGB_ALPHA       = 35913,
		GL_COMPRESSED_SLUMINANCE       = 35914,
		GL_COMPRESSED_SLUMINANCE_ALPHA = 35915;

	/** GetTarget */
	public static final int
		GL_MAJOR_VERSION                       = 33307,
		GL_MINOR_VERSION                       = 33308,
		GL_NUM_EXTENSIONS                      = 33309,
		GL_CONTEXT_FLAGS                       = 33310,
		GL_CONTEXT_FLAG_FORWARD_COMPATIBLE_BIT = 1;

	/** Renamed tokens. */
	public static final int
		GL_COMPARE_REF_TO_TEXTURE = GL_COMPARE_R_TO_TEXTURE,
		GL_CLIP_DISTANCE0         = GL_CLIP_PLANE0,
		GL_CLIP_DISTANCE1         = GL_CLIP_PLANE1,
		GL_CLIP_DISTANCE2         = GL_CLIP_PLANE2,
		GL_CLIP_DISTANCE3         = GL_CLIP_PLANE3,
		GL_CLIP_DISTANCE4         = GL_CLIP_PLANE4,
		GL_CLIP_DISTANCE5         = GL_CLIP_PLANE5,
		GL_CLIP_DISTANCE6         = 12294,
		GL_CLIP_DISTANCE7         = 12295,
		GL_MAX_CLIP_DISTANCES     = GL_MAX_CLIP_PLANES,
		GL_MAX_VARYING_COMPONENTS = GL_MAX_VARYING_FLOATS;

	/** Accepted by the {@code pname} parameters of GetVertexAttribdv, GetVertexAttribfv, GetVertexAttribiv, GetVertexAttribIuiv and GetVertexAttribIiv. */
	public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER = 35069;

	/** Returned by the {@code type} parameter of GetActiveUniform. */
	public static final int
		GL_SAMPLER_1D_ARRAY              = 36288,
		GL_SAMPLER_2D_ARRAY              = 36289,
		GL_SAMPLER_1D_ARRAY_SHADOW       = 36291,
		GL_SAMPLER_2D_ARRAY_SHADOW       = 36292,
		GL_SAMPLER_CUBE_SHADOW           = 36293,
		GL_UNSIGNED_INT_VEC2             = 36294,
		GL_UNSIGNED_INT_VEC3             = 36295,
		GL_UNSIGNED_INT_VEC4             = 36296,
		GL_INT_SAMPLER_1D                = 36297,
		GL_INT_SAMPLER_2D                = 36298,
		GL_INT_SAMPLER_3D                = 36299,
		GL_INT_SAMPLER_CUBE              = 36300,
		GL_INT_SAMPLER_1D_ARRAY          = 36302,
		GL_INT_SAMPLER_2D_ARRAY          = 36303,
		GL_UNSIGNED_INT_SAMPLER_1D       = 36305,
		GL_UNSIGNED_INT_SAMPLER_2D       = 36306,
		GL_UNSIGNED_INT_SAMPLER_3D       = 36307,
		GL_UNSIGNED_INT_SAMPLER_CUBE     = 36308,
		GL_UNSIGNED_INT_SAMPLER_1D_ARRAY = 36310,
		GL_UNSIGNED_INT_SAMPLER_2D_ARRAY = 36311;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_MIN_PROGRAM_TEXEL_OFFSET = 35076,
		GL_MAX_PROGRAM_TEXEL_OFFSET = 35077;

	/** Accepted by the {@code mode} parameter of BeginConditionalRender. */
	public static final int
		GL_QUERY_WAIT              = 36371,
		GL_QUERY_NO_WAIT           = 36372,
		GL_QUERY_BY_REGION_WAIT    = 36373,
		GL_QUERY_BY_REGION_NO_WAIT = 36374;

	/** Accepted by the {@code access} parameter of MapBufferRange. */
	public static final int
		GL_MAP_READ_BIT              = 1,
		GL_MAP_WRITE_BIT             = 2,
		GL_MAP_INVALIDATE_RANGE_BIT  = 4,
		GL_MAP_INVALIDATE_BUFFER_BIT = 8,
		GL_MAP_FLUSH_EXPLICIT_BIT    = 16,
		GL_MAP_UNSYNCHRONIZED_BIT    = 32;

	/** Accepted by the {@code pname} parameter of GetBufferParameteriv. */
	public static final int
		GL_BUFFER_ACCESS_FLAGS = 37151,
		GL_BUFFER_MAP_LENGTH   = 37152,
		GL_BUFFER_MAP_OFFSET   = 37153;

	/** Accepted by the {@code target} parameter of ClampColor and the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_CLAMP_VERTEX_COLOR   = 35098,
		GL_CLAMP_FRAGMENT_COLOR = 35099,
		GL_CLAMP_READ_COLOR     = 35100;

	/** Accepted by the {@code clamp} parameter of ClampColor. */
	public static final int GL_FIXED_ONLY = 35101;

	/**
	 * Accepted by the {@code internalformat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, and RenderbufferStorage, and
	 * returned in the {@code data} parameter of GetTexLevelParameter and GetRenderbufferParameteriv.
	 */
	public static final int
		GL_DEPTH_COMPONENT32F = 36012,
		GL_DEPTH32F_STENCIL8  = 36013;

	/**
	 * Accepted by the {@code type} parameter of DrawPixels, ReadPixels, TexImage1D, TexImage2D, TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D, and
	 * GetTexImage.
	 */
	public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV = 36269;

	/** Accepted by the {@code value} parameter of GetTexLevelParameter. */
	public static final int
		GL_TEXTURE_RED_TYPE       = 35856,
		GL_TEXTURE_GREEN_TYPE     = 35857,
		GL_TEXTURE_BLUE_TYPE      = 35858,
		GL_TEXTURE_ALPHA_TYPE     = 35859,
		GL_TEXTURE_LUMINANCE_TYPE = 35860,
		GL_TEXTURE_INTENSITY_TYPE = 35861,
		GL_TEXTURE_DEPTH_TYPE     = 35862;

	/** Returned by the {@code params} parameter of GetTexLevelParameter. */
	public static final int GL_UNSIGNED_NORMALIZED = 35863;

	/** Accepted by the {@code internalFormat} parameter of TexImage1D, TexImage2D, and TexImage3D. */
	public static final int
		GL_RGBA32F = 34836,
		GL_RGB32F  = 34837,
		GL_RGBA16F = 34842,
		GL_RGB16F  = 34843;

	/** Accepted by the {@code internalformat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, and RenderbufferStorage. */
	public static final int GL_R11F_G11F_B10F = 35898;

	/**
	 * Accepted by the {@code type} parameter of DrawPixels, ReadPixels, TexImage1D, TexImage2D, GetTexImage, TexImage3D, TexSubImage1D, TexSubImage2D,
	 * TexSubImage3D, GetHistogram, GetMinmax, ConvolutionFilter1D, ConvolutionFilter2D, ConvolutionFilter3D, GetConvolutionFilter, SeparableFilter2D,
	 * GetSeparableFilter, ColorTable, ColorSubTable, and GetColorTable.
	 */
	public static final int GL_UNSIGNED_INT_10F_11F_11F_REV = 35899;

	/** Accepted by the {@code internalformat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, and RenderbufferStorage. */
	public static final int GL_RGB9_E5 = 35901;

	/**
	 * Accepted by the {@code type} parameter of DrawPixels, ReadPixels, TexImage1D, TexImage2D, GetTexImage, TexImage3D, TexSubImage1D, TexSubImage2D,
	 * TexSubImage3D, GetHistogram, GetMinmax, ConvolutionFilter1D, ConvolutionFilter2D, ConvolutionFilter3D, GetConvolutionFilter, SeparableFilter2D,
	 * GetSeparableFilter, ColorTable, ColorSubTable, and GetColorTable.
	 */
	public static final int GL_UNSIGNED_INT_5_9_9_9_REV = 35902;

	/** Accepted by the {@code pname} parameter of GetTexLevelParameterfv and GetTexLevelParameteriv. */
	public static final int GL_TEXTURE_SHARED_SIZE = 35903;

	/**
	 * Accepted by the {@code target} parameter of BindFramebuffer, CheckFramebufferStatus, FramebufferTexture{1D|2D|3D}, FramebufferRenderbuffer, and
	 * GetFramebufferAttachmentParameteriv.
	 */
	public static final int
		GL_FRAMEBUFFER      = 36160,
		GL_READ_FRAMEBUFFER = 36008,
		GL_DRAW_FRAMEBUFFER = 36009;

	/**
	 * Accepted by the {@code target} parameter of BindRenderbuffer, RenderbufferStorage, and GetRenderbufferParameteriv, and returned by
	 * GetFramebufferAttachmentParameteriv.
	 */
	public static final int GL_RENDERBUFFER = 36161;

	/** Accepted by the {@code internalformat} parameter of RenderbufferStorage. */
	public static final int
		GL_STENCIL_INDEX1  = 36166,
		GL_STENCIL_INDEX4  = 36167,
		GL_STENCIL_INDEX8  = 36168,
		GL_STENCIL_INDEX16 = 36169;

	/** Accepted by the {@code pname} parameter of GetRenderbufferParameteriv. */
	public static final int
		GL_RENDERBUFFER_WIDTH           = 36162,
		GL_RENDERBUFFER_HEIGHT          = 36163,
		GL_RENDERBUFFER_INTERNAL_FORMAT = 36164,
		GL_RENDERBUFFER_RED_SIZE        = 36176,
		GL_RENDERBUFFER_GREEN_SIZE      = 36177,
		GL_RENDERBUFFER_BLUE_SIZE       = 36178,
		GL_RENDERBUFFER_ALPHA_SIZE      = 36179,
		GL_RENDERBUFFER_DEPTH_SIZE      = 36180,
		GL_RENDERBUFFER_STENCIL_SIZE    = 36181,
		GL_RENDERBUFFER_SAMPLES         = 36011;

	/** Accepted by the {@code pname} parameter of GetFramebufferAttachmentParameteriv. */
	public static final int
		GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE           = 36048,
		GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME           = 36049,
		GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL         = 36050,
		GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051,
		GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER         = 36052,
		GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING        = 33296,
		GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE        = 33297,
		GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE              = 33298,
		GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE            = 33299,
		GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE             = 33300,
		GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE            = 33301,
		GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE            = 33302,
		GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE          = 33303;

	/** Returned in {@code params} by GetFramebufferAttachmentParameteriv. */
	public static final int
		GL_FRAMEBUFFER_DEFAULT = 33304,
		GL_INDEX               = 33314;

	/** Accepted by the {@code attachment} parameter of FramebufferTexture{1D|2D|3D}, FramebufferRenderbuffer, and GetFramebufferAttachmentParameteriv. */
	public static final int
		GL_COLOR_ATTACHMENT0        = 36064,
		GL_COLOR_ATTACHMENT1        = 36065,
		GL_COLOR_ATTACHMENT2        = 36066,
		GL_COLOR_ATTACHMENT3        = 36067,
		GL_COLOR_ATTACHMENT4        = 36068,
		GL_COLOR_ATTACHMENT5        = 36069,
		GL_COLOR_ATTACHMENT6        = 36070,
		GL_COLOR_ATTACHMENT7        = 36071,
		GL_COLOR_ATTACHMENT8        = 36072,
		GL_COLOR_ATTACHMENT9        = 36073,
		GL_COLOR_ATTACHMENT10       = 36074,
		GL_COLOR_ATTACHMENT11       = 36075,
		GL_COLOR_ATTACHMENT12       = 36076,
		GL_COLOR_ATTACHMENT13       = 36077,
		GL_COLOR_ATTACHMENT14       = 36078,
		GL_COLOR_ATTACHMENT15       = 36079,
		GL_COLOR_ATTACHMENT16       = 36080,
		GL_COLOR_ATTACHMENT17       = 36081,
		GL_COLOR_ATTACHMENT18       = 36082,
		GL_COLOR_ATTACHMENT19       = 36083,
		GL_COLOR_ATTACHMENT20       = 36084,
		GL_COLOR_ATTACHMENT21       = 36085,
		GL_COLOR_ATTACHMENT22       = 36086,
		GL_COLOR_ATTACHMENT23       = 36087,
		GL_COLOR_ATTACHMENT24       = 36088,
		GL_COLOR_ATTACHMENT25       = 36089,
		GL_COLOR_ATTACHMENT26       = 36090,
		GL_COLOR_ATTACHMENT27       = 36091,
		GL_COLOR_ATTACHMENT28       = 36092,
		GL_COLOR_ATTACHMENT29       = 36093,
		GL_COLOR_ATTACHMENT30       = 36094,
		GL_COLOR_ATTACHMENT31       = 36095,
		GL_DEPTH_ATTACHMENT         = 36096,
		GL_STENCIL_ATTACHMENT       = 36128,
		GL_DEPTH_STENCIL_ATTACHMENT = 33306;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_MAX_SAMPLES = 36183;

	/** Returned by CheckFramebufferStatus(). */
	public static final int
		GL_FRAMEBUFFER_COMPLETE                      = 36053,
		GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT         = 36054,
		GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055,
		GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER        = 36059,
		GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER        = 36060,
		GL_FRAMEBUFFER_UNSUPPORTED                   = 36061,
		GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE        = 36182,
		GL_FRAMEBUFFER_UNDEFINED                     = 33305;

	/** Accepted by the {@code pname} parameters of GetIntegerv, GetFloatv,  and GetDoublev. */
	public static final int
		GL_FRAMEBUFFER_BINDING      = 36006,
		GL_DRAW_FRAMEBUFFER_BINDING = 36006,
		GL_READ_FRAMEBUFFER_BINDING = 36010,
		GL_RENDERBUFFER_BINDING     = 36007,
		GL_MAX_COLOR_ATTACHMENTS    = 36063,
		GL_MAX_RENDERBUFFER_SIZE    = 34024;

	/** Returned by GetError(). */
	public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 1286;

	/**
	 * Accepted by the {@code format} parameter of DrawPixels, ReadPixels, TexImage1D, TexImage2D, TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D, and
	 * GetTexImage, by the {@code type} parameter of CopyPixels, by the {@code internalformat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D,
	 * CopyTexImage2D, and RenderbufferStorage, and returned in the {@code data} parameter of GetTexLevelParameter and GetRenderbufferParameteriv.
	 */
	public static final int GL_DEPTH_STENCIL = 34041;

	/**
	 * Accepted by the {@code type} parameter of DrawPixels, ReadPixels, TexImage1D, TexImage2D, TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D, and
	 * GetTexImage.
	 */
	public static final int GL_UNSIGNED_INT_24_8 = 34042;

	/**
	 * Accepted by the {@code internalformat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, and RenderbufferStorage, and
	 * returned in the {@code data} parameter of GetTexLevelParameter and GetRenderbufferParameteriv.
	 */
	public static final int GL_DEPTH24_STENCIL8 = 35056;

	/** Accepted by the {@code value} parameter of GetTexLevelParameter. */
	public static final int GL_TEXTURE_STENCIL_SIZE = 35057;

	/**
	 * Accepted by the {@code type} parameter of DrawPixels, ReadPixels, TexImage1D, TexImage2D, TexImage3D, GetTexImage, TexSubImage1D, TexSubImage2D,
	 * TexSubImage3D, GetHistogram, GetMinmax, ConvolutionFilter1D, ConvolutionFilter2D, GetConvolutionFilter, SeparableFilter2D, GetSeparableFilter,
	 * ColorTable, ColorSubTable, and GetColorTable.
	 * 
	 * <p>Accepted by the {@code type} argument of VertexPointer, NormalPointer, ColorPointer, SecondaryColorPointer, FogCoordPointer, TexCoordPointer, and
	 * VertexAttribPointer.</p>
	 */
	public static final int GL_HALF_FLOAT = 5131;

	/** Accepted by the {@code internalFormat} parameter of TexImage1D, TexImage2D, and TexImage3D. */
	public static final int
		GL_RGBA32UI = 36208,
		GL_RGB32UI  = 36209,
		GL_RGBA16UI = 36214,
		GL_RGB16UI  = 36215,
		GL_RGBA8UI  = 36220,
		GL_RGB8UI   = 36221,
		GL_RGBA32I  = 36226,
		GL_RGB32I   = 36227,
		GL_RGBA16I  = 36232,
		GL_RGB16I   = 36233,
		GL_RGBA8I   = 36238,
		GL_RGB8I    = 36239;

	/** Accepted by the {@code format} parameter of TexImage1D, TexImage2D, TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D, DrawPixels and ReadPixels. */
	public static final int
		GL_RED_INTEGER   = 36244,
		GL_GREEN_INTEGER = 36245,
		GL_BLUE_INTEGER  = 36246,
		GL_ALPHA_INTEGER = 36247,
		GL_RGB_INTEGER   = 36248,
		GL_RGBA_INTEGER  = 36249,
		GL_BGR_INTEGER   = 36250,
		GL_BGRA_INTEGER  = 36251;

	/** Accepted by the {@code target} parameter of TexParameteri, TexParameteriv, TexParameterf, TexParameterfv, GenerateMipmap, and BindTexture. */
	public static final int
		GL_TEXTURE_1D_ARRAY = 35864,
		GL_TEXTURE_2D_ARRAY = 35866;

	/** Accepted by the {@code target} parameter of TexImage3D, TexSubImage3D, CopyTexSubImage3D, CompressedTexImage3D, and CompressedTexSubImage3D. */
	public static final int GL_PROXY_TEXTURE_2D_ARRAY = 35867;

	/**
	 * Accepted by the {@code target} parameter of TexImage2D, TexSubImage2D, CopyTexImage2D, CopyTexSubImage2D, CompressedTexImage2D, and
	 * CompressedTexSubImage2D.
	 */
	public static final int GL_PROXY_TEXTURE_1D_ARRAY = 35865;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv and GetFloatv. */
	public static final int
		GL_TEXTURE_BINDING_1D_ARRAY = 35868,
		GL_TEXTURE_BINDING_2D_ARRAY = 35869,
		GL_MAX_ARRAY_TEXTURE_LAYERS = 35071;

	/**
	 * Accepted by the {@code internalformat} parameter of TexImage2D, CopyTexImage2D, and CompressedTexImage2D and the {@code format} parameter of
	 * CompressedTexSubImage2D.
	 */
	public static final int
		GL_COMPRESSED_RED_RGTC1        = 36283,
		GL_COMPRESSED_SIGNED_RED_RGTC1 = 36284,
		GL_COMPRESSED_RG_RGTC2         = 36285,
		GL_COMPRESSED_SIGNED_RG_RGTC2  = 36286;

	/** Accepted by the {@code internalFormat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D, and CopyTexImage2D. */
	public static final int
		GL_R8             = 33321,
		GL_R16            = 33322,
		GL_RG8            = 33323,
		GL_RG16           = 33324,
		GL_R16F           = 33325,
		GL_R32F           = 33326,
		GL_RG16F          = 33327,
		GL_RG32F          = 33328,
		GL_R8I            = 33329,
		GL_R8UI           = 33330,
		GL_R16I           = 33331,
		GL_R16UI          = 33332,
		GL_R32I           = 33333,
		GL_R32UI          = 33334,
		GL_RG8I           = 33335,
		GL_RG8UI          = 33336,
		GL_RG16I          = 33337,
		GL_RG16UI         = 33338,
		GL_RG32I          = 33339,
		GL_RG32UI         = 33340,
		GL_RG             = 33319,
		GL_COMPRESSED_RED = 33317,
		GL_COMPRESSED_RG  = 33318;

	/** Accepted by the {@code format} parameter of TexImage3D, TexImage2D, TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D, and ReadPixels. */
	public static final int GL_RG_INTEGER = 33320;

	/**
	 * Accepted by the {@code target} parameters of BindBuffer, BufferData, BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, GetBufferPointerv,
	 * BindBufferRange, BindBufferOffset and BindBufferBase.
	 */
	public static final int GL_TRANSFORM_FEEDBACK_BUFFER = 35982;

	/** Accepted by the {@code param} parameter of GetIntegeri_v and GetBooleani_v. */
	public static final int
		GL_TRANSFORM_FEEDBACK_BUFFER_START = 35972,
		GL_TRANSFORM_FEEDBACK_BUFFER_SIZE  = 35973;

	/**
	 * Accepted by the {@code param} parameter of GetIntegeri_v and GetBooleani_v, and by the {@code pname} parameter of GetBooleanv,
	 * GetDoublev, GetIntegerv, and GetFloatv.
	 */
	public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING = 35983;

	/** Accepted by the {@code bufferMode} parameter of TransformFeedbackVaryings. */
	public static final int
		GL_INTERLEAVED_ATTRIBS = 35980,
		GL_SEPARATE_ATTRIBS    = 35981;

	/** Accepted by the {@code target} parameter of BeginQuery, EndQuery, and GetQueryiv. */
	public static final int
		GL_PRIMITIVES_GENERATED                  = 35975,
		GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN = 35976;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev.
	 */
	public static final int GL_RASTERIZER_DISCARD = 35977;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv. */
	public static final int
		GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS = 35978,
		GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS       = 35979,
		GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS    = 35968;

	/** Accepted by the {@code pname} parameter of GetProgramiv. */
	public static final int
		GL_TRANSFORM_FEEDBACK_VARYINGS           = 35971,
		GL_TRANSFORM_FEEDBACK_BUFFER_MODE        = 35967,
		GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH = 35958;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_VERTEX_ARRAY_BINDING = 34229;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev.
	 */
	public static final int GL_FRAMEBUFFER_SRGB = 36281;

	/** Accepted by the {@code internalFormat} parameter of TexImage1D, TexImage2D, and TexImage3D. */
	public static final int
		GL_R8_SNORM     = 36756,
		GL_RG8_SNORM    = 36757,
		GL_RGB8_SNORM   = 36758,
		GL_RGBA8_SNORM  = 36759,
		GL_R16_SNORM    = 36760,
		GL_RG16_SNORM   = 36761,
		GL_RGB16_SNORM  = 36762,
		GL_RGBA16_SNORM = 36763;

	/** Returned by GetTexLevelParameter and GetFramebufferAttachmentParameter. */
	public static final int GL_SIGNED_NORMALIZED = 36764;

	/** Returned by the {@code type} parameter of GetActiveUniform. */
	public static final int
		GL_SAMPLER_BUFFER               = 36290,
		GL_INT_SAMPLER_2D_RECT          = 36301,
		GL_INT_SAMPLER_BUFFER           = 36304,
		GL_UNSIGNED_INT_SAMPLER_2D_RECT = 36309,
		GL_UNSIGNED_INT_SAMPLER_BUFFER  = 36312;

	/**
	 * Accepted by the target parameters of BindBuffer, BufferData, BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, GetBufferPointerv, MapBufferRange,
	 * FlushMappedBufferRange, GetBufferParameteriv, BindBufferRange, BindBufferBase, and CopyBufferSubData.
	 */
	public static final int
		GL_COPY_READ_BUFFER  = 36662,
		GL_COPY_WRITE_BUFFER = 36663;

	/** Accepted by the {@code cap} parameter of Enable, Disable and IsEnabled. */
	public static final int GL_PRIMITIVE_RESTART = 36765;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_PRIMITIVE_RESTART_INDEX = 36766;

	/**
	 * Accepted by the {@code target} parameter of BindBuffer, BufferData, BufferSubData, MapBuffer, MapBufferRange, BindTexture, UnmapBuffer,
	 * GetBufferSubData, GetBufferParameteriv, GetBufferPointerv, and TexBuffer, and the {@code pname} parameter of GetBooleanv, GetDoublev, GetFloatv, and
	 * GetIntegerv.
	 */
	public static final int GL_TEXTURE_BUFFER = 35882;

	/** Accepted by the {@code pname} parameters of GetBooleanv, GetDoublev, GetFloatv, and GetIntegerv. */
	public static final int
		GL_MAX_TEXTURE_BUFFER_SIZE           = 35883,
		GL_TEXTURE_BINDING_BUFFER            = 35884,
		GL_TEXTURE_BUFFER_DATA_STORE_BINDING = 35885;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable and IsEnabled; by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv and
	 * GetDoublev; and by the {@code target} parameter of BindTexture, GetTexParameterfv, GetTexParameteriv, TexParameterf, TexParameteri, TexParameterfv and
	 * TexParameteriv.
	 */
	public static final int GL_TEXTURE_RECTANGLE = 34037;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv and GetDoublev. */
	public static final int GL_TEXTURE_BINDING_RECTANGLE = 34038;

	/** Accepted by the {@code target} parameter of GetTexLevelParameteriv, GetTexLevelParameterfv, GetTexParameteriv and TexImage2D. */
	public static final int GL_PROXY_TEXTURE_RECTANGLE = 34039;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv and GetFloatv. */
	public static final int GL_MAX_RECTANGLE_TEXTURE_SIZE = 34040;

	/** Returned by {@code type} parameter of GetActiveUniform when the location {@code index} for program object {@code program} is of type sampler2DRect. */
	public static final int GL_SAMPLER_2D_RECT = 35683;

	/**
	 * Returned by {@code type} parameter of GetActiveUniform when the location {@code index} for program object {@code program} is of type
	 * sampler2DRectShadow.
	 */
	public static final int GL_SAMPLER_2D_RECT_SHADOW = 35684;

	/** Accepted by the {@code target} parameters of BindBuffer, BufferData, BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, and GetBufferPointerv. */
	public static final int GL_UNIFORM_BUFFER = 35345;

	/** Accepted by the {@code pname} parameter of GetIntegeri_v, GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_UNIFORM_BUFFER_BINDING = 35368;

	/** Accepted by the {@code pname} parameter of GetIntegeri_v. */
	public static final int
		GL_UNIFORM_BUFFER_START = 35369,
		GL_UNIFORM_BUFFER_SIZE  = 35370;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_MAX_VERTEX_UNIFORM_BLOCKS                = 35371,
		GL_MAX_GEOMETRY_UNIFORM_BLOCKS              = 35372,
		GL_MAX_FRAGMENT_UNIFORM_BLOCKS              = 35373,
		GL_MAX_COMBINED_UNIFORM_BLOCKS              = 35374,
		GL_MAX_UNIFORM_BUFFER_BINDINGS              = 35375,
		GL_MAX_UNIFORM_BLOCK_SIZE                   = 35376,
		GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS   = 35377,
		GL_MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 35378,
		GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 35379,
		GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT          = 35380;

	/** Accepted by the {@code pname} parameter of GetProgramiv. */
	public static final int
		GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH = 35381,
		GL_ACTIVE_UNIFORM_BLOCKS                = 35382;

	/** Accepted by the {@code pname} parameter of GetActiveUniformsiv. */
	public static final int
		GL_UNIFORM_TYPE          = 35383,
		GL_UNIFORM_SIZE          = 35384,
		GL_UNIFORM_NAME_LENGTH   = 35385,
		GL_UNIFORM_BLOCK_INDEX   = 35386,
		GL_UNIFORM_OFFSET        = 35387,
		GL_UNIFORM_ARRAY_STRIDE  = 35388,
		GL_UNIFORM_MATRIX_STRIDE = 35389,
		GL_UNIFORM_IS_ROW_MAJOR  = 35390;

	/** Accepted by the {@code pname} parameter of GetActiveUniformBlockiv. */
	public static final int
		GL_UNIFORM_BLOCK_BINDING                       = 35391,
		GL_UNIFORM_BLOCK_DATA_SIZE                     = 35392,
		GL_UNIFORM_BLOCK_NAME_LENGTH                   = 35393,
		GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS               = 35394,
		GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES        = 35395,
		GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER   = 35396,
		GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER = 35397,
		GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 35398;

	/** Accepted by the {@code pname} parameter of GetIntegerv. */
	public static final int GL_CONTEXT_PROFILE_MASK = 37158;

	/** Context profile bits. */
	public static final int
		GL_CONTEXT_CORE_PROFILE_BIT          = 1,
		GL_CONTEXT_COMPATIBILITY_PROFILE_BIT = 2;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_MAX_VERTEX_OUTPUT_COMPONENTS   = 37154,
		GL_MAX_GEOMETRY_INPUT_COMPONENTS  = 37155,
		GL_MAX_GEOMETRY_OUTPUT_COMPONENTS = 37156,
		GL_MAX_FRAGMENT_INPUT_COMPONENTS  = 37157;

	/** Accepted by the {@code mode} parameter of ProvokingVertex. */
	public static final int
		GL_FIRST_VERTEX_CONVENTION = 36429,
		GL_LAST_VERTEX_CONVENTION  = 36430;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_PROVOKING_VERTEX                         = 36431,
		GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION = 36428;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv and
	 * GetDoublev.
	 */
	public static final int GL_TEXTURE_CUBE_MAP_SEAMLESS = 34895;

	/** Accepted by the {@code pname} parameter of GetMultisamplefv. */
	public static final int GL_SAMPLE_POSITION = 36432;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev.
	 */
	public static final int GL_SAMPLE_MASK = 36433;

	/** Accepted by the {@code target} parameter of GetBooleani_v and GetIntegeri_v. */
	public static final int GL_SAMPLE_MASK_VALUE = 36434;

	/** Accepted by the {@code target} parameter of BindTexture and TexImage2DMultisample. */
	public static final int GL_TEXTURE_2D_MULTISAMPLE = 37120;

	/** Accepted by the {@code target} parameter of TexImage2DMultisample. */
	public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE = 37121;

	/** Accepted by the {@code target} parameter of BindTexture and TexImage3DMultisample. */
	public static final int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 37122;

	/** Accepted by the {@code target} parameter of TexImage3DMultisample. */
	public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY = 37123;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv. */
	public static final int
		GL_MAX_SAMPLE_MASK_WORDS                = 36441,
		GL_MAX_COLOR_TEXTURE_SAMPLES            = 37134,
		GL_MAX_DEPTH_TEXTURE_SAMPLES            = 37135,
		GL_MAX_INTEGER_SAMPLES                  = 37136,
		GL_TEXTURE_BINDING_2D_MULTISAMPLE       = 37124,
		GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY = 37125;

	/** Accepted by the {@code pname} parameter of GetTexLevelParameter. */
	public static final int
		GL_TEXTURE_SAMPLES                = 37126,
		GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 37127;

	/** Returned by the {@code type} parameter of GetActiveUniform. */
	public static final int
		GL_SAMPLER_2D_MULTISAMPLE                    = 37128,
		GL_INT_SAMPLER_2D_MULTISAMPLE                = 37129,
		GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE       = 37130,
		GL_SAMPLER_2D_MULTISAMPLE_ARRAY              = 37131,
		GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY          = 37132,
		GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37133;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev.
	 */
	public static final int GL_DEPTH_CLAMP = 34383;

	/** Accepted by the {@code type} parameter of CreateShader and returned by the {@code params} parameter of GetShaderiv. */
	public static final int GL_GEOMETRY_SHADER = 36313;

	/** Accepted by the {@code pname} parameter of ProgramParameteri and GetProgramiv. */
	public static final int
		GL_GEOMETRY_VERTICES_OUT = 36314,
		GL_GEOMETRY_INPUT_TYPE   = 36315,
		GL_GEOMETRY_OUTPUT_TYPE  = 36316;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS     = 35881,
		GL_MAX_GEOMETRY_UNIFORM_COMPONENTS      = 36319,
		GL_MAX_GEOMETRY_OUTPUT_VERTICES         = 36320,
		GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS = 36321;

	/** Accepted by the {@code mode} parameter of Begin, DrawArrays, MultiDrawArrays, DrawElements, MultiDrawElements, and DrawRangeElements. */
	public static final int
		GL_LINES_ADJACENCY          = 10,
		GL_LINE_STRIP_ADJACENCY     = 11,
		GL_TRIANGLES_ADJACENCY      = 12,
		GL_TRIANGLE_STRIP_ADJACENCY = 13;

	/** Returned by CheckFramebufferStatus. */
	public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS = 36264;

	/** Accepted by the {@code pname} parameter of GetFramebufferAttachment- Parameteriv. */
	public static final int GL_FRAMEBUFFER_ATTACHMENT_LAYERED = 36263;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetIntegerv, GetFloatv, GetDoublev, and
	 * GetBooleanv.
	 */
	public static final int GL_PROGRAM_POINT_SIZE = 34370;

	/** Accepted as the {@code pname} parameter of GetInteger64v. */
	public static final int GL_MAX_SERVER_WAIT_TIMEOUT = 37137;

	/** Accepted as the {@code pname} parameter of GetSynciv. */
	public static final int
		GL_OBJECT_TYPE    = 37138,
		GL_SYNC_CONDITION = 37139,
		GL_SYNC_STATUS    = 37140,
		GL_SYNC_FLAGS     = 37141;

	/** Returned in {@code values} for GetSynciv {@code pname} OBJECT_TYPE. */
	public static final int GL_SYNC_FENCE = 37142;

	/** Returned in {@code values} for GetSynciv {@code pname} SYNC_CONDITION. */
	public static final int GL_SYNC_GPU_COMMANDS_COMPLETE = 37143;

	/** Returned in {@code values} for GetSynciv {@code pname} SYNC_STATUS. */
	public static final int
		GL_UNSIGNALED = 37144,
		GL_SIGNALED   = 37145;

	/** Accepted in the {@code flags} parameter of ClientWaitSync. */
	public static final int GL_SYNC_FLUSH_COMMANDS_BIT = 1;

	/** Accepted in the {@code timeout} parameter of WaitSync. */
	public static final long GL_TIMEOUT_IGNORED = -1L;

	/** Returned by ClientWaitSync. */
	public static final int
		GL_ALREADY_SIGNALED    = 37146,
		GL_TIMEOUT_EXPIRED     = 37147,
		GL_CONDITION_SATISFIED = 37148,
		GL_WAIT_FAILED         = 37149;

	/**
	 * Accepted by the {@code src} and {@code dst} parameters of BlendFunc and BlendFunci, and by the {@code srcRGB}, {@code dstRGB}, {@code srcAlpha} and
	 * {@code dstAlpha} parameters of BlendFuncSeparate and BlendFuncSeparatei.
	 */
	public static final int
		GL_SRC1_COLOR           = 35065,
		GL_ONE_MINUS_SRC1_COLOR = 35066,
		GL_ONE_MINUS_SRC1_ALPHA = 35067;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv and GetDoublev. */
	public static final int GL_MAX_DUAL_SOURCE_DRAW_BUFFERS = 35068;

	/** Accepted by the {@code target} parameter of BeginQuery, EndQuery, and GetQueryiv. */
	public static final int GL_ANY_SAMPLES_PASSED = 35887;

	/** Accepted by the {@code value} parameter of the GetBooleanv, GetIntegerv, GetInteger64v, GetFloatv and GetDoublev functions. */
	public static final int GL_SAMPLER_BINDING = 35097;

	/**
	 * Accepted by the {@code internalFormat} parameter of TexImage1D, TexImage2D, TexImage3D, CopyTexImage1D, CopyTexImage2D, RenderbufferStorage and
	 * RenderbufferStorageMultisample.
	 */
	public static final int GL_RGB10_A2UI = 36975;

	/** Accepted by the {@code pname} parameters of TexParameteri, TexParameterf, TexParameteriv, TexParameterfv, GetTexParameterfv, and GetTexParameteriv. */
	public static final int
		GL_TEXTURE_SWIZZLE_R = 36418,
		GL_TEXTURE_SWIZZLE_G = 36419,
		GL_TEXTURE_SWIZZLE_B = 36420,
		GL_TEXTURE_SWIZZLE_A = 36421;

	/** Accepted by the {@code pname} parameters of TexParameteriv,  TexParameterfv, GetTexParameterfv, and GetTexParameteriv. */
	public static final int GL_TEXTURE_SWIZZLE_RGBA = 36422;

	/** Accepted by the {@code target} parameter of BeginQuery, EndQuery, and GetQueryiv. */
	public static final int GL_TIME_ELAPSED = 35007;

	/**
	 * Accepted by the {@code target} parameter of GetQueryiv and QueryCounter. Accepted by the {@code value} parameter of GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv, and GetDoublev.
	 */
	public static final int GL_TIMESTAMP = 36392;

	/** Accepted by the {@code pname} parameters of GetVertexAttribdv, GetVertexAttribfv, and GetVertexAttribiv. */
	public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR = 35070;

	/**
	 * Accepted by the {@code type} parameter of VertexAttribPointer, VertexPointer, NormalPointer, ColorPointer, SecondaryColorPointer, TexCoordPointer,
	 * VertexAttribP{1234}ui, VertexP*, TexCoordP*, MultiTexCoordP*, NormalP3ui, ColorP*, SecondaryColorP* and VertexAttribP*.
	 */
	public static final int GL_INT_2_10_10_10_REV = 36255;

	/**
	 * Accepted by the {@code target} parameters of BindBuffer, BufferData, BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, GetBufferPointerv,
	 * MapBufferRange, FlushMappedBufferRange, GetBufferParameteriv, and CopyBufferSubData.
	 */
	public static final int GL_DRAW_INDIRECT_BUFFER = 36671;

	/** Accepted by the {@code value} parameter of GetIntegerv, GetBooleanv, GetFloatv, and GetDoublev. */
	public static final int GL_DRAW_INDIRECT_BUFFER_BINDING = 36675;

	/** Accepted by the {@code pname} parameter of GetProgramiv. */
	public static final int GL_GEOMETRY_SHADER_INVOCATIONS = 34943;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, GetDoublev, and GetInteger64v. */
	public static final int
		GL_MAX_GEOMETRY_SHADER_INVOCATIONS    = 36442,
		GL_MIN_FRAGMENT_INTERPOLATION_OFFSET  = 36443,
		GL_MAX_FRAGMENT_INTERPOLATION_OFFSET  = 36444,
		GL_FRAGMENT_INTERPOLATION_OFFSET_BITS = 36445;

	/** Returned in the {@code type} parameter of GetActiveUniform, and GetTransformFeedbackVarying. */
	public static final int
		GL_DOUBLE_VEC2   = 36860,
		GL_DOUBLE_VEC3   = 36861,
		GL_DOUBLE_VEC4   = 36862,
		GL_DOUBLE_MAT2   = 36678,
		GL_DOUBLE_MAT3   = 36679,
		GL_DOUBLE_MAT4   = 36680,
		GL_DOUBLE_MAT2x3 = 36681,
		GL_DOUBLE_MAT2x4 = 36682,
		GL_DOUBLE_MAT3x2 = 36683,
		GL_DOUBLE_MAT3x4 = 36684,
		GL_DOUBLE_MAT4x2 = 36685,
		GL_DOUBLE_MAT4x3 = 36686;

	/**
	 * Accepted by the {@code cap} parameter of Enable, Disable, and IsEnabled, and by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev.
	 */
	public static final int GL_SAMPLE_SHADING = 35894;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv. */
	public static final int GL_MIN_SAMPLE_SHADING_VALUE = 35895;

	/** Accepted by the {@code pname} parameter of GetProgramStageiv. */
	public static final int
		GL_ACTIVE_SUBROUTINES                   = 36325,
		GL_ACTIVE_SUBROUTINE_UNIFORMS           = 36326,
		GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS  = 36423,
		GL_ACTIVE_SUBROUTINE_MAX_LENGTH         = 36424,
		GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH = 36425;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, GetDoublev, and GetInteger64v. */
	public static final int
		GL_MAX_SUBROUTINES                  = 36327,
		GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS = 36328;

	/** Accepted by the {@code pname} parameter of GetActiveSubroutineUniformiv. */
	public static final int
		GL_NUM_COMPATIBLE_SUBROUTINES = 36426,
		GL_COMPATIBLE_SUBROUTINES     = 36427;

	/** Accepted by the {@code mode} parameter of Begin and all vertex array functions that implicitly call Begin. */
	public static final int GL_PATCHES = 14;

	/** Accepted by the {@code pname} parameter of PatchParameteri, GetBooleanv, GetDoublev, GetFloatv, GetIntegerv, and GetInteger64v. */
	public static final int GL_PATCH_VERTICES = 36466;

	/** Accepted by the {@code pname} parameter of PatchParameterfv, GetBooleanv, GetDoublev, GetFloatv, and GetIntegerv, and GetInteger64v. */
	public static final int
		GL_PATCH_DEFAULT_INNER_LEVEL = 36467,
		GL_PATCH_DEFAULT_OUTER_LEVEL = 36468;

	/** Accepted by the {@code pname} parameter of GetProgramiv. */
	public static final int
		GL_TESS_CONTROL_OUTPUT_VERTICES = 36469,
		GL_TESS_GEN_MODE                = 36470,
		GL_TESS_GEN_SPACING             = 36471,
		GL_TESS_GEN_VERTEX_ORDER        = 36472,
		GL_TESS_GEN_POINT_MODE          = 36473;

	/** Returned by GetProgramiv when {@code pname} is TESS_GEN_MODE. */
	public static final int GL_ISOLINES = 36474;

	/** Returned by GetProgramiv when {@code pname} is TESS_GEN_SPACING. */
	public static final int
		GL_FRACTIONAL_ODD  = 36475,
		GL_FRACTIONAL_EVEN = 36476;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetFloatv, GetIntegerv, and GetInteger64v. */
	public static final int
		GL_MAX_PATCH_VERTICES                              = 36477,
		GL_MAX_TESS_GEN_LEVEL                              = 36478,
		GL_MAX_TESS_CONTROL_UNIFORM_COMPONENTS             = 36479,
		GL_MAX_TESS_EVALUATION_UNIFORM_COMPONENTS          = 36480,
		GL_MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS            = 36481,
		GL_MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS         = 36482,
		GL_MAX_TESS_CONTROL_OUTPUT_COMPONENTS              = 36483,
		GL_MAX_TESS_PATCH_COMPONENTS                       = 36484,
		GL_MAX_TESS_CONTROL_TOTAL_OUTPUT_COMPONENTS        = 36485,
		GL_MAX_TESS_EVALUATION_OUTPUT_COMPONENTS           = 36486,
		GL_MAX_TESS_CONTROL_UNIFORM_BLOCKS                 = 36489,
		GL_MAX_TESS_EVALUATION_UNIFORM_BLOCKS              = 36490,
		GL_MAX_TESS_CONTROL_INPUT_COMPONENTS               = 34924,
		GL_MAX_TESS_EVALUATION_INPUT_COMPONENTS            = 34925,
		GL_MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS    = 36382,
		GL_MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = 36383;

	/** Accepted by the {@code pname} parameter of GetActiveUniformBlockiv. */
	public static final int
		GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER    = 34032,
		GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER = 34033;

	/** Accepted by the {@code type} parameter of CreateShader and returned by the {@code params} parameter of GetShaderiv. */
	public static final int
		GL_TESS_EVALUATION_SHADER = 36487,
		GL_TESS_CONTROL_SHADER    = 36488;

	/** Accepted by the {@code target} parameter of TexParameteri, TexParameteriv, TexParameterf, TexParameterfv, BindTexture, and GenerateMipmap. */
	public static final int GL_TEXTURE_CUBE_MAP_ARRAY = 36873;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv and GetFloatv. */
	public static final int GL_TEXTURE_BINDING_CUBE_MAP_ARRAY = 36874;

	/** Accepted by the {@code target} parameter of TexImage3D, TexSubImage3D, CompressedTeximage3D, CompressedTexSubImage3D and CopyTexSubImage3D. */
	public static final int GL_PROXY_TEXTURE_CUBE_MAP_ARRAY = 36875;

	/** Returned by the {@code type} parameter of GetActiveUniform. */
	public static final int
		GL_SAMPLER_CUBE_MAP_ARRAY              = 36876,
		GL_SAMPLER_CUBE_MAP_ARRAY_SHADOW       = 36877,
		GL_INT_SAMPLER_CUBE_MAP_ARRAY          = 36878,
		GL_UNSIGNED_INT_SAMPLER_CUBE_MAP_ARRAY = 36879;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_MIN_PROGRAM_TEXTURE_GATHER_OFFSET = 36446,
		GL_MAX_PROGRAM_TEXTURE_GATHER_OFFSET = 36447;

	/** Accepted by the {@code target} parameter of BindTransformFeedback. */
	public static final int GL_TRANSFORM_FEEDBACK = 36386;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv. */
	public static final int
		GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = 36387,
		GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = 36388,
		GL_TRANSFORM_FEEDBACK_BINDING       = 36389;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetIntegerv, and GetFloatv. */
	public static final int
		GL_MAX_TRANSFORM_FEEDBACK_BUFFERS = 36464,
		GL_MAX_VERTEX_STREAMS             = 36465;


	/** Accepted by the {@code value} parameter of GetBooleanv, GetIntegerv, GetInteger64v, GetFloatv, and GetDoublev. */
	public static final int
		GL_SHADER_COMPILER                  = 36346,
		GL_SHADER_BINARY_FORMATS            = 36344,
		GL_NUM_SHADER_BINARY_FORMATS        = 36345,
		GL_MAX_VERTEX_UNIFORM_VECTORS       = 36347,
		GL_MAX_VARYING_VECTORS              = 36348,
		GL_MAX_FRAGMENT_UNIFORM_VECTORS     = 36349,
		GL_IMPLEMENTATION_COLOR_READ_TYPE   = 35738,
		GL_IMPLEMENTATION_COLOR_READ_FORMAT = 35739;

	/** Accepted by the {@code type} parameter of VertexAttribPointer. */
	public static final int GL_FIXED = 5132;

	/** Accepted by the {@code precisiontype} parameter of GetShaderPrecisionFormat. */
	public static final int
		GL_LOW_FLOAT    = 36336,
		GL_MEDIUM_FLOAT = 36337,
		GL_HIGH_FLOAT   = 36338,
		GL_LOW_INT      = 36339,
		GL_MEDIUM_INT   = 36340,
		GL_HIGH_INT     = 36341;

	/** Accepted by the {@code format} parameter of most commands taking sized internal formats. */
	public static final int GL_RGB565 = 36194;

	/** Accepted by the {@code pname} parameter of ProgramParameteri and GetProgramiv. */
	public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 33367;

	/** Accepted by the {@code pname} parameter of GetProgramiv. */
	public static final int GL_PROGRAM_BINARY_LENGTH = 34625;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetInteger64v, GetFloatv and GetDoublev. */
	public static final int
		GL_NUM_PROGRAM_BINARY_FORMATS = 34814,
		GL_PROGRAM_BINARY_FORMATS     = 34815;

	/** Accepted by {@code stages} parameter to UseProgramStages. */
	public static final int
		GL_VERTEX_SHADER_BIT          = 1,
		GL_FRAGMENT_SHADER_BIT        = 2,
		GL_GEOMETRY_SHADER_BIT        = 4,
		GL_TESS_CONTROL_SHADER_BIT    = 8,
		GL_TESS_EVALUATION_SHADER_BIT = 16,
		GL_ALL_SHADER_BITS            = -1;

	/** Accepted by the {@code pname} parameter of ProgramParameteri and GetProgramiv. */
	public static final int GL_PROGRAM_SEPARABLE = 33368;

	/** Accepted by {@code type} parameter to GetProgramPipelineiv. */
	public static final int GL_ACTIVE_PROGRAM = 33369;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetInteger64v, GetFloatv, and GetDoublev. */
	public static final int GL_PROGRAM_PIPELINE_BINDING = 33370;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, GetDoublev and GetInteger64v. */
	public static final int
		GL_MAX_VIEWPORTS                   = 33371,
		GL_VIEWPORT_SUBPIXEL_BITS          = 33372,
		GL_VIEWPORT_BOUNDS_RANGE           = 33373,
		GL_LAYER_PROVOKING_VERTEX          = 33374,
		GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 33375;

	/** Returned in the {@code data} parameter from a Get query with a {@code pname} of LAYER_PROVOKING_VERTEX or VIEWPORT_INDEX_PROVOKING_VERTEX. */
	public static final int GL_UNDEFINED_VERTEX = 33376;

	/** Renamed tokens. */
	public static final int
		GL_COPY_READ_BUFFER_BINDING  = GL_COPY_READ_BUFFER,
		GL_COPY_WRITE_BUFFER_BINDING = GL_COPY_WRITE_BUFFER,
		GL_TRANSFORM_FEEDBACK_ACTIVE = GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE,
		GL_TRANSFORM_FEEDBACK_PAUSED = GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED;

	/**
	 * Accepted by the {@code internalformat} parameter of TexImage2D, TexImage3D, CopyTexImage2D, CopyTexImage3D, CompressedTexImage2D, and
	 * CompressedTexImage3D and the {@code format} parameter of CompressedTexSubImage2D and CompressedTexSubImage3D.
	 */
	public static final int
		GL_COMPRESSED_RGBA_BPTC_UNORM         = 36492,
		GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM   = 36493,
		GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT   = 36494,
		GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT = 36495;

	/** Accepted by the {@code pname} parameter of PixelStore[fi], GetBooleanv, GetIntegerv, GetInteger64v, GetFloatv, and GetDoublev. */
	public static final int
		GL_UNPACK_COMPRESSED_BLOCK_WIDTH  = 37159,
		GL_UNPACK_COMPRESSED_BLOCK_HEIGHT = 37160,
		GL_UNPACK_COMPRESSED_BLOCK_DEPTH  = 37161,
		GL_UNPACK_COMPRESSED_BLOCK_SIZE   = 37162,
		GL_PACK_COMPRESSED_BLOCK_WIDTH    = 37163,
		GL_PACK_COMPRESSED_BLOCK_HEIGHT   = 37164,
		GL_PACK_COMPRESSED_BLOCK_DEPTH    = 37165,
		GL_PACK_COMPRESSED_BLOCK_SIZE     = 37166;

	/** Accepted by the {@code target} parameter of BindBufferBase and BindBufferRange. */
	public static final int GL_ATOMIC_COUNTER_BUFFER = 37568;

	/**
	 * Accepted by the {@code pname} parameter of GetBooleani_v, GetIntegeri_v, GetFloati_v, GetDoublei_v, GetInteger64i_v, GetBooleanv, GetIntegerv,
	 * GetInteger64v, GetFloatv, GetDoublev, and GetActiveAtomicCounterBufferiv.
	 */
	public static final int GL_ATOMIC_COUNTER_BUFFER_BINDING = 37569;

	/** Accepted by the {@code pname} parameter of GetIntegeri_64v. */
	public static final int
		GL_ATOMIC_COUNTER_BUFFER_START = 37570,
		GL_ATOMIC_COUNTER_BUFFER_SIZE  = 37571;

	/** Accepted by the {@code pname} parameter of GetActiveAtomicCounterBufferiv. */
	public static final int
		GL_ATOMIC_COUNTER_BUFFER_DATA_SIZE                            = 37572,
		GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS               = 37573,
		GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES        = 37574,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER          = 37575,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER    = 37576,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER = 37577,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER        = 37578,
		GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER        = 37579;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetInteger64v, GetFloatv, and GetDoublev. */
	public static final int
		GL_MAX_VERTEX_ATOMIC_COUNTER_BUFFERS          = 37580,
		GL_MAX_TESS_CONTROL_ATOMIC_COUNTER_BUFFERS    = 37581,
		GL_MAX_TESS_EVALUATION_ATOMIC_COUNTER_BUFFERS = 37582,
		GL_MAX_GEOMETRY_ATOMIC_COUNTER_BUFFERS        = 37583,
		GL_MAX_FRAGMENT_ATOMIC_COUNTER_BUFFERS        = 37584,
		GL_MAX_COMBINED_ATOMIC_COUNTER_BUFFERS        = 37585,
		GL_MAX_VERTEX_ATOMIC_COUNTERS                 = 37586,
		GL_MAX_TESS_CONTROL_ATOMIC_COUNTERS           = 37587,
		GL_MAX_TESS_EVALUATION_ATOMIC_COUNTERS        = 37588,
		GL_MAX_GEOMETRY_ATOMIC_COUNTERS               = 37589,
		GL_MAX_FRAGMENT_ATOMIC_COUNTERS               = 37590,
		GL_MAX_COMBINED_ATOMIC_COUNTERS               = 37591,
		GL_MAX_ATOMIC_COUNTER_BUFFER_SIZE             = 37592,
		GL_MAX_ATOMIC_COUNTER_BUFFER_BINDINGS         = 37596;

	/** Accepted by the {@code pname} parameter of GetProgramiv. */
	public static final int GL_ACTIVE_ATOMIC_COUNTER_BUFFERS = 37593;

	/** Accepted by the {@code pname} parameter of GetActiveUniformsiv. */
	public static final int GL_UNIFORM_ATOMIC_COUNTER_BUFFER_INDEX = 37594;

	/** Returned in {@code params} by GetActiveUniform and GetActiveUniformsiv. */
	public static final int GL_UNSIGNED_INT_ATOMIC_COUNTER = 37595;

	/** Accepted by the {@code value} parameter of GetTexParameter{if}v. */
	public static final int GL_TEXTURE_IMMUTABLE_FORMAT = 37167;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, GetDoublev, and GetInteger64v. */
	public static final int
		GL_MAX_IMAGE_UNITS                               = 36664,
		GL_MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS = 36665,
		GL_MAX_IMAGE_SAMPLES                             = 36973,
		GL_MAX_VERTEX_IMAGE_UNIFORMS                     = 37066,
		GL_MAX_TESS_CONTROL_IMAGE_UNIFORMS               = 37067,
		GL_MAX_TESS_EVALUATION_IMAGE_UNIFORMS            = 37068,
		GL_MAX_GEOMETRY_IMAGE_UNIFORMS                   = 37069,
		GL_MAX_FRAGMENT_IMAGE_UNIFORMS                   = 37070,
		GL_MAX_COMBINED_IMAGE_UNIFORMS                   = 37071;

	/** Accepted by the {@code target} parameter of GetIntegeri_v and GetBooleani_v. */
	public static final int
		GL_IMAGE_BINDING_NAME    = 36666,
		GL_IMAGE_BINDING_LEVEL   = 36667,
		GL_IMAGE_BINDING_LAYERED = 36668,
		GL_IMAGE_BINDING_LAYER   = 36669,
		GL_IMAGE_BINDING_ACCESS  = 36670,
		GL_IMAGE_BINDING_FORMAT  = 36974;

	/** Accepted by the {@code barriers} parameter of MemoryBarrier. */
	public static final int
		GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT = 1,
		GL_ELEMENT_ARRAY_BARRIER_BIT       = 2,
		GL_UNIFORM_BARRIER_BIT             = 4,
		GL_TEXTURE_FETCH_BARRIER_BIT       = 8,
		GL_SHADER_IMAGE_ACCESS_BARRIER_BIT = 32,
		GL_COMMAND_BARRIER_BIT             = 64,
		GL_PIXEL_BUFFER_BARRIER_BIT        = 128,
		GL_TEXTURE_UPDATE_BARRIER_BIT      = 256,
		GL_BUFFER_UPDATE_BARRIER_BIT       = 512,
		GL_FRAMEBUFFER_BARRIER_BIT         = 1024,
		GL_TRANSFORM_FEEDBACK_BARRIER_BIT  = 2048,
		GL_ATOMIC_COUNTER_BARRIER_BIT      = 4096,
		GL_ALL_BARRIER_BITS                = -1;

	/** Returned by the {@code type} parameter of GetActiveUniform. */
	public static final int
		GL_IMAGE_1D                                = 36940,
		GL_IMAGE_2D                                = 36941,
		GL_IMAGE_3D                                = 36942,
		GL_IMAGE_2D_RECT                           = 36943,
		GL_IMAGE_CUBE                              = 36944,
		GL_IMAGE_BUFFER                            = 36945,
		GL_IMAGE_1D_ARRAY                          = 36946,
		GL_IMAGE_2D_ARRAY                          = 36947,
		GL_IMAGE_CUBE_MAP_ARRAY                    = 36948,
		GL_IMAGE_2D_MULTISAMPLE                    = 36949,
		GL_IMAGE_2D_MULTISAMPLE_ARRAY              = 36950,
		GL_INT_IMAGE_1D                            = 36951,
		GL_INT_IMAGE_2D                            = 36952,
		GL_INT_IMAGE_3D                            = 36953,
		GL_INT_IMAGE_2D_RECT                       = 36954,
		GL_INT_IMAGE_CUBE                          = 36955,
		GL_INT_IMAGE_BUFFER                        = 36956,
		GL_INT_IMAGE_1D_ARRAY                      = 36957,
		GL_INT_IMAGE_2D_ARRAY                      = 36958,
		GL_INT_IMAGE_CUBE_MAP_ARRAY                = 36959,
		GL_INT_IMAGE_2D_MULTISAMPLE                = 36960,
		GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY          = 36961,
		GL_UNSIGNED_INT_IMAGE_1D                   = 36962,
		GL_UNSIGNED_INT_IMAGE_2D                   = 36963,
		GL_UNSIGNED_INT_IMAGE_3D                   = 36964,
		GL_UNSIGNED_INT_IMAGE_2D_RECT              = 36965,
		GL_UNSIGNED_INT_IMAGE_CUBE                 = 36966,
		GL_UNSIGNED_INT_IMAGE_BUFFER               = 36967,
		GL_UNSIGNED_INT_IMAGE_1D_ARRAY             = 36968,
		GL_UNSIGNED_INT_IMAGE_2D_ARRAY             = 36969,
		GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY       = 36970,
		GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE       = 36971,
		GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 36972;

	/** Accepted by the {@code value} parameter of GetTexParameteriv, GetTexParameterfv, GetTexParameterIiv, and GetTexParameterIuiv. */
	public static final int GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 37063;

	/**
	 * Returned in the {@code data} parameter of GetTexParameteriv, GetTexParameterfv, GetTexParameterIiv, and GetTexParameterIuiv when {@code value} is
	 * IMAGE_FORMAT_COMPATIBILITY_TYPE.
	 */
	public static final int
		GL_IMAGE_FORMAT_COMPATIBILITY_BY_SIZE  = 37064,
		GL_IMAGE_FORMAT_COMPATIBILITY_BY_CLASS = 37065;

	/** Accepted by the {@code pname} parameter of GetInternalformativ. */
	public static final int GL_NUM_SAMPLE_COUNTS = 37760;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetInteger64v, GetFloatv, and GetDoublev. */
	public static final int GL_MIN_MAP_BUFFER_ALIGNMENT = 37052;

	/** No. of supported Shading Language Versions. Accepted by the {@code pname} parameter of GetIntegerv. */
	public static final int GL_NUM_SHADING_LANGUAGE_VERSIONS = 33513;

	/** Vertex attrib array has unconverted doubles. Accepted by the {@code pname} parameter of GetVertexAttribiv. */
	public static final int GL_VERTEX_ATTRIB_ARRAY_LONG = 34638;

	/** Accepted by the {@code internalformat} parameter of CompressedTexImage2D. */
	public static final int
		GL_COMPRESSED_RGB8_ETC2                      = 37492,
		GL_COMPRESSED_SRGB8_ETC2                     = 37493,
		GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2  = 37494,
		GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 37495,
		GL_COMPRESSED_RGBA8_ETC2_EAC                 = 37496,
		GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC          = 37497,
		GL_COMPRESSED_R11_EAC                        = 37488,
		GL_COMPRESSED_SIGNED_R11_EAC                 = 37489,
		GL_COMPRESSED_RG11_EAC                       = 37490,
		GL_COMPRESSED_SIGNED_RG11_EAC                = 37491;

	/** Accepted by the {@code target} parameter of Enable and Disable. */
	public static final int GL_PRIMITIVE_RESTART_FIXED_INDEX = 36201;

	/** Accepted by the {@code target} parameter of BeginQuery, EndQuery, GetQueryIndexediv and GetQueryiv. */
	public static final int GL_ANY_SAMPLES_PASSED_CONSERVATIVE = 36202;

	/** Accepted by the {@code value} parameter of the GetInteger* functions. */
	public static final int GL_MAX_ELEMENT_INDEX = 36203;

	/** Accepted by the {@code pname} parameters of GetTexParameterfv and  GetTexParameteriv. */
	public static final int GL_TEXTURE_IMMUTABLE_LEVELS = 33503;

	/** Accepted by the {@code type} parameter of CreateShader and returned in the {@code params} parameter by GetShaderiv. */
	public static final int GL_COMPUTE_SHADER = 37305;

	/** Accepted by the {@code pname} parameter of GetIntegerv, GetBooleanv, GetFloatv, GetDoublev and GetInteger64v. */
	public static final int
		GL_MAX_COMPUTE_UNIFORM_BLOCKS              = 37307,
		GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS         = 37308,
		GL_MAX_COMPUTE_IMAGE_UNIFORMS              = 37309,
		GL_MAX_COMPUTE_SHARED_MEMORY_SIZE          = 33378,
		GL_MAX_COMPUTE_UNIFORM_COMPONENTS          = 33379,
		GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS      = 33380,
		GL_MAX_COMPUTE_ATOMIC_COUNTERS             = 33381,
		GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 33382,
		GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS      = 37099;

	/** Accepted by the {@code pname} parameter of GetIntegeri_v, GetBooleani_v, GetFloati_v, GetDoublei_v and GetInteger64i_v. */
	public static final int
		GL_MAX_COMPUTE_WORK_GROUP_COUNT = 37310,
		GL_MAX_COMPUTE_WORK_GROUP_SIZE  = 37311;

	/** Accepted by the {@code pname} parameter of GetProgramiv. */
	public static final int GL_COMPUTE_WORK_GROUP_SIZE = 33383;

	/** Accepted by the {@code pname} parameter of GetActiveUniformBlockiv. */
	public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER = 37100;

	/** Accepted by the {@code pname} parameter of GetActiveAtomicCounterBufferiv. */
	public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_COMPUTE_SHADER = 37101;

	/** Accepted by the {@code target} parameters of BindBuffer, BufferData, BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, and GetBufferPointerv. */
	public static final int GL_DISPATCH_INDIRECT_BUFFER = 37102;

	/** Accepted by the {@code value} parameter of GetIntegerv, GetBooleanv, GetInteger64v, GetFloatv, and GetDoublev. */
	public static final int GL_DISPATCH_INDIRECT_BUFFER_BINDING = 37103;

	/** Accepted by the {@code stages} parameter of UseProgramStages. */
	public static final int GL_COMPUTE_SHADER_BIT = 32;

	/** Tokens accepted by the {@code target} parameters of Enable, Disable, and  IsEnabled. */
	public static final int
		GL_DEBUG_OUTPUT             = 37600,
		GL_DEBUG_OUTPUT_SYNCHRONOUS = 33346;

	/** Returned by GetIntegerv when {@code pname} is CONTEXT_FLAGS. */
	public static final int GL_CONTEXT_FLAG_DEBUG_BIT = 2;

	/** Tokens accepted by the {@code value} parameters of GetBooleanv, GetIntegerv,  GetFloatv, GetDoublev and GetInteger64v. */
	public static final int
		GL_MAX_DEBUG_MESSAGE_LENGTH         = 37187,
		GL_MAX_DEBUG_LOGGED_MESSAGES        = 37188,
		GL_DEBUG_LOGGED_MESSAGES            = 37189,
		GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH = 33347,
		GL_MAX_DEBUG_GROUP_STACK_DEPTH      = 33388,
		GL_DEBUG_GROUP_STACK_DEPTH          = 33389,
		GL_MAX_LABEL_LENGTH                 = 33512;

	/** Tokens accepted by the {@code pname} parameter of GetPointerv. */
	public static final int
		GL_DEBUG_CALLBACK_FUNCTION   = 33348,
		GL_DEBUG_CALLBACK_USER_PARAM = 33349;

	/**
	 * Tokens accepted or provided by the {@code source} parameters of DebugMessageControl, DebugMessageInsert and DEBUGPROC, and the {@code sources} parameter
	 * of GetDebugMessageLog.
	 */
	public static final int
		GL_DEBUG_SOURCE_API             = 33350,
		GL_DEBUG_SOURCE_WINDOW_SYSTEM   = 33351,
		GL_DEBUG_SOURCE_SHADER_COMPILER = 33352,
		GL_DEBUG_SOURCE_THIRD_PARTY     = 33353,
		GL_DEBUG_SOURCE_APPLICATION     = 33354,
		GL_DEBUG_SOURCE_OTHER           = 33355;

	/**
	 * Tokens accepted or provided by the {@code type} parameters of DebugMessageControl, DebugMessageInsert and DEBUGPROC, and the {@code types} parameter of
	 * GetDebugMessageLog.
	 */
	public static final int
		GL_DEBUG_TYPE_ERROR               = 33356,
		GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 33357,
		GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR  = 33358,
		GL_DEBUG_TYPE_PORTABILITY         = 33359,
		GL_DEBUG_TYPE_PERFORMANCE         = 33360,
		GL_DEBUG_TYPE_OTHER               = 33361,
		GL_DEBUG_TYPE_MARKER              = 33384;

	/** Tokens accepted or provided by the {@code type} parameters of DebugMessageControl and DEBUGPROC, and the {@code types} parameter of GetDebugMessageLog. */
	public static final int
		GL_DEBUG_TYPE_PUSH_GROUP = 33385,
		GL_DEBUG_TYPE_POP_GROUP  = 33386;

	/**
	 * Tokens accepted or provided by the {@code severity} parameters of DebugMessageControl, DebugMessageInsert and DEBUGPROC callback functions, and the
	 * {@code severities} parameter of GetDebugMessageLog.
	 */
	public static final int
		GL_DEBUG_SEVERITY_HIGH         = 37190,
		GL_DEBUG_SEVERITY_MEDIUM       = 37191,
		GL_DEBUG_SEVERITY_LOW          = 37192,
		GL_DEBUG_SEVERITY_NOTIFICATION = 33387;

	/** Tokens accepted or provided by the {@code identifier} parameters of ObjectLabel and GetObjectLabel. */
	public static final int
		GL_BUFFER           = 33504,
		GL_SHADER           = 33505,
		GL_PROGRAM          = 33506,
		GL_QUERY            = 33507,
		GL_PROGRAM_PIPELINE = 33508,
		GL_SAMPLER          = 33510,
		GL_DISPLAY_LIST     = 33511;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, GetDoublev, and GetInteger64v. */
	public static final int GL_MAX_UNIFORM_LOCATIONS = 33390;

	/**
	 * Accepted by the {@code pname} parameter of FramebufferParameteri, GetFramebufferParameteriv, NamedFramebufferParameteriEXT, and
	 * GetNamedFramebufferParameterivEXT.
	 */
	public static final int
		GL_FRAMEBUFFER_DEFAULT_WIDTH                  = 37648,
		GL_FRAMEBUFFER_DEFAULT_HEIGHT                 = 37649,
		GL_FRAMEBUFFER_DEFAULT_LAYERS                 = 37650,
		GL_FRAMEBUFFER_DEFAULT_SAMPLES                = 37651,
		GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 37652;

	/** Accepted by the {@code pname} parameter of GetIntegerv, GetBooleanv, GetInteger64v, GetFloatv, and GetDoublev. */
	public static final int
		GL_MAX_FRAMEBUFFER_WIDTH   = 37653,
		GL_MAX_FRAMEBUFFER_HEIGHT  = 37654,
		GL_MAX_FRAMEBUFFER_LAYERS  = 37655,
		GL_MAX_FRAMEBUFFER_SAMPLES = 37656;

	/** Accepted by the {@code pname} parameter of GetInternalformativ and GetInternalformati64v. */
	public static final int
		GL_INTERNALFORMAT_SUPPORTED               = 33391,
		GL_INTERNALFORMAT_PREFERRED               = 33392,
		GL_INTERNALFORMAT_RED_SIZE                = 33393,
		GL_INTERNALFORMAT_GREEN_SIZE              = 33394,
		GL_INTERNALFORMAT_BLUE_SIZE               = 33395,
		GL_INTERNALFORMAT_ALPHA_SIZE              = 33396,
		GL_INTERNALFORMAT_DEPTH_SIZE              = 33397,
		GL_INTERNALFORMAT_STENCIL_SIZE            = 33398,
		GL_INTERNALFORMAT_SHARED_SIZE             = 33399,
		GL_INTERNALFORMAT_RED_TYPE                = 33400,
		GL_INTERNALFORMAT_GREEN_TYPE              = 33401,
		GL_INTERNALFORMAT_BLUE_TYPE               = 33402,
		GL_INTERNALFORMAT_ALPHA_TYPE              = 33403,
		GL_INTERNALFORMAT_DEPTH_TYPE              = 33404,
		GL_INTERNALFORMAT_STENCIL_TYPE            = 33405,
		GL_MAX_WIDTH                              = 33406,
		GL_MAX_HEIGHT                             = 33407,
		GL_MAX_DEPTH                              = 33408,
		GL_MAX_LAYERS                             = 33409,
		GL_MAX_COMBINED_DIMENSIONS                = 33410,
		GL_COLOR_COMPONENTS                       = 33411,
		GL_DEPTH_COMPONENTS                       = 33412,
		GL_STENCIL_COMPONENTS                     = 33413,
		GL_COLOR_RENDERABLE                       = 33414,
		GL_DEPTH_RENDERABLE                       = 33415,
		GL_STENCIL_RENDERABLE                     = 33416,
		GL_FRAMEBUFFER_RENDERABLE                 = 33417,
		GL_FRAMEBUFFER_RENDERABLE_LAYERED         = 33418,
		GL_FRAMEBUFFER_BLEND                      = 33419,
		GL_READ_PIXELS                            = 33420,
		GL_READ_PIXELS_FORMAT                     = 33421,
		GL_READ_PIXELS_TYPE                       = 33422,
		GL_TEXTURE_IMAGE_FORMAT                   = 33423,
		GL_TEXTURE_IMAGE_TYPE                     = 33424,
		GL_GET_TEXTURE_IMAGE_FORMAT               = 33425,
		GL_GET_TEXTURE_IMAGE_TYPE                 = 33426,
		GL_MIPMAP                                 = 33427,
		GL_MANUAL_GENERATE_MIPMAP                 = 33428,
		GL_AUTO_GENERATE_MIPMAP                   = 33429,
		GL_COLOR_ENCODING                         = 33430,
		GL_SRGB_READ                              = 33431,
		GL_SRGB_WRITE                             = 33432,
		GL_FILTER                                 = 33434,
		GL_VERTEX_TEXTURE                         = 33435,
		GL_TESS_CONTROL_TEXTURE                   = 33436,
		GL_TESS_EVALUATION_TEXTURE                = 33437,
		GL_GEOMETRY_TEXTURE                       = 33438,
		GL_FRAGMENT_TEXTURE                       = 33439,
		GL_COMPUTE_TEXTURE                        = 33440,
		GL_TEXTURE_SHADOW                         = 33441,
		GL_TEXTURE_GATHER                         = 33442,
		GL_TEXTURE_GATHER_SHADOW                  = 33443,
		GL_SHADER_IMAGE_LOAD                      = 33444,
		GL_SHADER_IMAGE_STORE                     = 33445,
		GL_SHADER_IMAGE_ATOMIC                    = 33446,
		GL_IMAGE_TEXEL_SIZE                       = 33447,
		GL_IMAGE_COMPATIBILITY_CLASS              = 33448,
		GL_IMAGE_PIXEL_FORMAT                     = 33449,
		GL_IMAGE_PIXEL_TYPE                       = 33450,
		GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_TEST    = 33452,
		GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_TEST  = 33453,
		GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_WRITE   = 33454,
		GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_WRITE = 33455,
		GL_TEXTURE_COMPRESSED_BLOCK_WIDTH         = 33457,
		GL_TEXTURE_COMPRESSED_BLOCK_HEIGHT        = 33458,
		GL_TEXTURE_COMPRESSED_BLOCK_SIZE          = 33459,
		GL_CLEAR_BUFFER                           = 33460,
		GL_TEXTURE_VIEW                           = 33461,
		GL_VIEW_COMPATIBILITY_CLASS               = 33462;

	/** Returned as possible responses for various {@code pname} queries to GetInternalformativ and GetInternalformati64v. */
	public static final int
		GL_FULL_SUPPORT              = 33463,
		GL_CAVEAT_SUPPORT            = 33464,
		GL_IMAGE_CLASS_4_X_32        = 33465,
		GL_IMAGE_CLASS_2_X_32        = 33466,
		GL_IMAGE_CLASS_1_X_32        = 33467,
		GL_IMAGE_CLASS_4_X_16        = 33468,
		GL_IMAGE_CLASS_2_X_16        = 33469,
		GL_IMAGE_CLASS_1_X_16        = 33470,
		GL_IMAGE_CLASS_4_X_8         = 33471,
		GL_IMAGE_CLASS_2_X_8         = 33472,
		GL_IMAGE_CLASS_1_X_8         = 33473,
		GL_IMAGE_CLASS_11_11_10      = 33474,
		GL_IMAGE_CLASS_10_10_10_2    = 33475,
		GL_VIEW_CLASS_128_BITS       = 33476,
		GL_VIEW_CLASS_96_BITS        = 33477,
		GL_VIEW_CLASS_64_BITS        = 33478,
		GL_VIEW_CLASS_48_BITS        = 33479,
		GL_VIEW_CLASS_32_BITS        = 33480,
		GL_VIEW_CLASS_24_BITS        = 33481,
		GL_VIEW_CLASS_16_BITS        = 33482,
		GL_VIEW_CLASS_8_BITS         = 33483,
		GL_VIEW_CLASS_S3TC_DXT1_RGB  = 33484,
		GL_VIEW_CLASS_S3TC_DXT1_RGBA = 33485,
		GL_VIEW_CLASS_S3TC_DXT3_RGBA = 33486,
		GL_VIEW_CLASS_S3TC_DXT5_RGBA = 33487,
		GL_VIEW_CLASS_RGTC1_RED      = 33488,
		GL_VIEW_CLASS_RGTC2_RG       = 33489,
		GL_VIEW_CLASS_BPTC_UNORM     = 33490,
		GL_VIEW_CLASS_BPTC_FLOAT     = 33491;

	/**
	 * Accepted by the {@code programInterface} parameter of GetProgramInterfaceiv, GetProgramResourceIndex, GetProgramResourceName, GetProgramResourceiv,
	 * GetProgramResourceLocation, and GetProgramResourceLocationIndex.
	 */
	public static final int
		GL_UNIFORM                            = 37601,
		GL_UNIFORM_BLOCK                      = 37602,
		GL_PROGRAM_INPUT                      = 37603,
		GL_PROGRAM_OUTPUT                     = 37604,
		GL_BUFFER_VARIABLE                    = 37605,
		GL_SHADER_STORAGE_BLOCK               = 37606,
		GL_VERTEX_SUBROUTINE                  = 37608,
		GL_TESS_CONTROL_SUBROUTINE            = 37609,
		GL_TESS_EVALUATION_SUBROUTINE         = 37610,
		GL_GEOMETRY_SUBROUTINE                = 37611,
		GL_FRAGMENT_SUBROUTINE                = 37612,
		GL_COMPUTE_SUBROUTINE                 = 37613,
		GL_VERTEX_SUBROUTINE_UNIFORM          = 37614,
		GL_TESS_CONTROL_SUBROUTINE_UNIFORM    = 37615,
		GL_TESS_EVALUATION_SUBROUTINE_UNIFORM = 37616,
		GL_GEOMETRY_SUBROUTINE_UNIFORM        = 37617,
		GL_FRAGMENT_SUBROUTINE_UNIFORM        = 37618,
		GL_COMPUTE_SUBROUTINE_UNIFORM         = 37619,
		GL_TRANSFORM_FEEDBACK_VARYING         = 37620;

	/** Accepted by the {@code pname} parameter of GetProgramInterfaceiv. */
	public static final int
		GL_ACTIVE_RESOURCES               = 37621,
		GL_MAX_NAME_LENGTH                = 37622,
		GL_MAX_NUM_ACTIVE_VARIABLES       = 37623,
		GL_MAX_NUM_COMPATIBLE_SUBROUTINES = 37624;

	/** Accepted in the {@code props} array of GetProgramResourceiv. */
	public static final int
		GL_NAME_LENGTH                          = 37625,
		GL_TYPE                                 = 37626,
		GL_ARRAY_SIZE                           = 37627,
		GL_OFFSET                               = 37628,
		GL_BLOCK_INDEX                          = 37629,
		GL_ARRAY_STRIDE                         = 37630,
		GL_MATRIX_STRIDE                        = 37631,
		GL_IS_ROW_MAJOR                         = 37632,
		GL_ATOMIC_COUNTER_BUFFER_INDEX          = 37633,
		GL_BUFFER_BINDING                       = 37634,
		GL_BUFFER_DATA_SIZE                     = 37635,
		GL_NUM_ACTIVE_VARIABLES                 = 37636,
		GL_ACTIVE_VARIABLES                     = 37637,
		GL_REFERENCED_BY_VERTEX_SHADER          = 37638,
		GL_REFERENCED_BY_TESS_CONTROL_SHADER    = 37639,
		GL_REFERENCED_BY_TESS_EVALUATION_SHADER = 37640,
		GL_REFERENCED_BY_GEOMETRY_SHADER        = 37641,
		GL_REFERENCED_BY_FRAGMENT_SHADER        = 37642,
		GL_REFERENCED_BY_COMPUTE_SHADER         = 37643,
		GL_TOP_LEVEL_ARRAY_SIZE                 = 37644,
		GL_TOP_LEVEL_ARRAY_STRIDE               = 37645,
		GL_LOCATION                             = 37646,
		GL_LOCATION_INDEX                       = 37647,
		GL_IS_PER_PATCH                         = 37607;

	/** Accepted by the {@code target} parameters of BindBuffer, BufferData, BufferSubData, MapBuffer, UnmapBuffer, GetBufferSubData, and GetBufferPointerv. */
	public static final int GL_SHADER_STORAGE_BUFFER = 37074;

	/**
	 * Accepted by the {@code pname} parameter of GetIntegerv, GetIntegeri_v, GetBooleanv, GetInteger64v, GetFloatv, GetDoublev, GetBooleani_v, GetIntegeri_v,
	 * GetFloati_v, GetDoublei_v, and GetInteger64i_v.
	 */
	public static final int GL_SHADER_STORAGE_BUFFER_BINDING = 37075;

	/** Accepted by the {@code pname} parameter of GetIntegeri_v, GetBooleani_v, GetIntegeri_v, GetFloati_v, GetDoublei_v, and GetInteger64i_v. */
	public static final int
		GL_SHADER_STORAGE_BUFFER_START = 37076,
		GL_SHADER_STORAGE_BUFFER_SIZE  = 37077;

	/** Accepted by the {@code pname} parameter of GetIntegerv, GetBooleanv, GetInteger64v, GetFloatv, and GetDoublev. */
	public static final int
		GL_MAX_VERTEX_SHADER_STORAGE_BLOCKS          = 37078,
		GL_MAX_GEOMETRY_SHADER_STORAGE_BLOCKS        = 37079,
		GL_MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS    = 37080,
		GL_MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = 37081,
		GL_MAX_FRAGMENT_SHADER_STORAGE_BLOCKS        = 37082,
		GL_MAX_COMPUTE_SHADER_STORAGE_BLOCKS         = 37083,
		GL_MAX_COMBINED_SHADER_STORAGE_BLOCKS        = 37084,
		GL_MAX_SHADER_STORAGE_BUFFER_BINDINGS        = 37085,
		GL_MAX_SHADER_STORAGE_BLOCK_SIZE             = 37086,
		GL_SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT    = 37087;

	/** Accepted in the {@code barriers} bitfield in glMemoryBarrier. */
	public static final int GL_SHADER_STORAGE_BARRIER_BIT = 8192;

	/** Alias for the existing token MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS. */
	public static final int GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 36665;

	/** Accepted by the {@code pname} parameter of TexParameter* and GetTexParameter*. */
	public static final int GL_DEPTH_STENCIL_TEXTURE_MODE = 37098;

	/** Accepted by the {@code pname} parameter of GetTexLevelParameter. */
	public static final int
		GL_TEXTURE_BUFFER_OFFSET = 37277,
		GL_TEXTURE_BUFFER_SIZE   = 37278;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 37279;

	/** Accepted by the {@code pname} parameters of GetTexParameterfv and  GetTexParameteriv. */
	public static final int
		GL_TEXTURE_VIEW_MIN_LEVEL  = 33499,
		GL_TEXTURE_VIEW_NUM_LEVELS = 33500,
		GL_TEXTURE_VIEW_MIN_LAYER  = 33501,
		GL_TEXTURE_VIEW_NUM_LAYERS = 33502;

	/** Accepted by the {@code pname} parameter of GetVertexAttrib*v. */
	public static final int
		GL_VERTEX_ATTRIB_BINDING         = 33492,
		GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 33493;

	/** Accepted by the {@code target} parameter of GetBooleani_v, GetIntegeri_v, GetFloati_v, GetDoublei_v, and GetInteger64i_v. */
	public static final int
		GL_VERTEX_BINDING_DIVISOR = 33494,
		GL_VERTEX_BINDING_OFFSET  = 33495,
		GL_VERTEX_BINDING_STRIDE  = 33496,
		GL_VERTEX_BINDING_BUFFER  = 36687;

	/** Accepted by the {@code pname} parameter of GetIntegerv, .... */
	public static final int
		GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 33497,
		GL_MAX_VERTEX_ATTRIB_BINDINGS        = 33498;

	/** Implementation-dependent state which constrains the maximum value of stride parameters to vertex array pointer-setting commands. */
	public static final int GL_MAX_VERTEX_ATTRIB_STRIDE = 33509;

	/**
	 * Implementations are not required to support primitive restart for separate patch primitives (primitive type PATCHES). Support can be queried by calling
	 * GetBooleanv with the symbolic constant PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED. A value of FALSE indicates that primitive restart is treated as
	 * disabled when drawing patches, no matter the value of the enables. A value of TRUE indicates that primitive restart behaves normally for patches.
	 */
	public static final int GL_PRIMITIVE_RESTART_FOR_PATCHES_SUPPORTED = 33313;

	/** Equivalent to {@link ARBTextureBufferObject#GL_TEXTURE_BUFFER_ARB TEXTURE_BUFFER_ARB} query, but named more consistently. */
	public static final int GL_TEXTURE_BUFFER_BINDING = 35882;

	/** Accepted in the {@code flags} parameter of {@link #glBufferStorage BufferStorage} and {@link ARBBufferStorage#glNamedBufferStorageEXT NamedBufferStorageEXT}. */
	public static final int
		GL_MAP_PERSISTENT_BIT  = 64,
		GL_MAP_COHERENT_BIT    = 128,
		GL_DYNAMIC_STORAGE_BIT = 256,
		GL_CLIENT_STORAGE_BIT  = 512;

	/** Accepted by the {@code pname} parameter of {@code GetBufferParameter&#123;indicesBuffer|i64&#125;v}. */
	public static final int
		GL_BUFFER_IMMUTABLE_STORAGE = 33311,
		GL_BUFFER_STORAGE_FLAGS     = 33312;

	/** Accepted by the {@code barriers} parameter of {@link GL42#glMemoryBarrier MemoryBarrier}. */
	public static final int GL_CLIENT_MAPPED_BUFFER_BARRIER_BIT = 16384;

	/** Accepted by the {@code pname} parameter for {@link GL42#glGetInternalformativ GetInternalformativ} and {@link GL43#glGetInternalformati64v GetInternalformati64v}. */
	public static final int GL_CLEAR_TEXTURE = 37733;

	/** Accepted in the {@code props} array of {@link GL43#glGetProgramResourceiv GetProgramResourceiv}. */
	public static final int
		GL_LOCATION_COMPONENT               = 37706,
		GL_TRANSFORM_FEEDBACK_BUFFER_INDEX  = 37707,
		GL_TRANSFORM_FEEDBACK_BUFFER_STRIDE = 37708;

	/** Accepted by the {@code pname} parameter of {@link GL15#glGetQueryObjectiv GetQueryObjectiv}, {@link GL15#glGetQueryObjectuiv GetQueryObjectuiv}, {@link GL33#glGetQueryObjecti64v GetQueryObjecti64v} and {@link GL33#glGetQueryObjectui64v GetQueryObjectui64v}. */
	public static final int GL_QUERY_RESULT_NO_WAIT = 37268;

	/**
	 * Accepted by the {@code target} parameter of {@link GL15#glBindBuffer BindBuffer}, {@link GL15#glBufferData BufferData}, {@link GL15#glBufferSubData BufferSubData},
	 * {@link GL15#glMapBuffer MapBuffer}, {@link GL15#glUnmapBuffer UnmapBuffer}, {@link GL30#glMapBufferRange MapBufferRange}, {@link GL15#glGetBufferSubData GetBufferSubData},
	 * {@link GL15#glGetBufferParameteriv GetBufferParameteriv}, {@link GL32#glGetBufferParameteri64v GetBufferParameteri64v}, {@link GL15#glGetBufferPointerv GetBufferPointerv},
	 * {@link GL43#glClearBufferSubData ClearBufferSubData}, and the {@code readtarget} and {@code writetarget} parameters of {@link GL31#glCopyBufferSubData CopyBufferSubData}.
	 */
	public static final int GL_QUERY_BUFFER = 37266;

	/**
	 * Accepted by the {@code pname} parameter of {@link GL11#glGetBooleanv GetBooleanv}, {@link GL11#glGetIntegerv GetIntegerv}, {@link GL11#glGetFloatv GetFloatv},
	 * and {@link GL11#glGetDoublev GetDoublev}.
	 */
	public static final int GL_QUERY_BUFFER_BINDING = 37267;

	/** Accepted in the {@code barriers} bitfield in {@link GL42#glMemoryBarrier MemoryBarrier}. */
	public static final int GL_QUERY_BUFFER_BARRIER_BIT = 32768;

	/**
	 * Accepted by the {@code param} parameter of TexParameter{if}, SamplerParameter{if} and SamplerParameter{if}v, and by the {@code params} parameter of
	 * TexParameter{if}v, TexParameterI{indicesBuffer ui}v and SamplerParameterI{indicesBuffer ui}v when their {@code pname} parameter is {@link GL11#GL_TEXTURE_WRAP_S TEXTURE_WRAP_S}, {@link GL11#GL_TEXTURE_WRAP_T TEXTURE_WRAP_T}, or
	 * {@link GL12#GL_TEXTURE_WRAP_R TEXTURE_WRAP_R},
	 */
	public static final int GL_MIRROR_CLAMP_TO_EDGE = 34627;

	/** Accepted by the {@code depth} parameter of {@link #glClipControl ClipControl}. */
	public static final int
		GL_NEGATIVE_ONE_TO_ONE = 37726,
		GL_ZERO_TO_ONE         = 37727;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int
		GL_CLIP_ORIGIN     = 37724,
		GL_CLIP_DEPTH_MODE = 37725;

	/** Accepted by the {@code mode} parameter of {@link GL30#glBeginConditionalRender BeginConditionalRender}. */
	public static final int
		GL_QUERY_WAIT_INVERTED              = 36375,
		GL_QUERY_NO_WAIT_INVERTED           = 36376,
		GL_QUERY_BY_REGION_WAIT_INVERTED    = 36377,
		GL_QUERY_BY_REGION_NO_WAIT_INVERTED = 36378;

	/** Accepted by the {@code pname} parameter of GetBooleanv, GetDoublev, GetFloatv, GetIntegerv, and GetInteger64v. */
	public static final int
		GL_MAX_CULL_DISTANCES                   = 33529,
		GL_MAX_COMBINED_CLIP_AND_CULL_DISTANCES = 33530;

	/** Accepted by the {@code pname} parameter of GetTextureParameter{if}v and GetTextureParameterI{indicesBuffer ui}v. */
	public static final int GL_TEXTURE_TARGET = 4102;

	/** Accepted by the {@code pname} parameter of GetQueryObjectiv. */
	public static final int GL_QUERY_TARGET = 33514;

	/** Accepted by the {@code pname} parameter of GetIntegerv, GetFloatv, GetBooleanv GetDoublev and GetInteger64v. */
	public static final int GL_CONTEXT_RELEASE_BEHAVIOR = 33531;

	/** Returned in {@code data} by GetIntegerv, GetFloatv, GetBooleanv GetDoublev and GetInteger64v when {@code pname} is {@link #GL_CONTEXT_RELEASE_BEHAVIOR CONTEXT_RELEASE_BEHAVIOR}. */
	public static final int GL_CONTEXT_RELEASE_BEHAVIOR_FLUSH = 33532;

	/** Returned by {@link #glGetGraphicsResetStatus GetGraphicsResetStatus}. */
	public static final int
		GL_GUILTY_CONTEXT_RESET   = 33363,
		GL_INNOCENT_CONTEXT_RESET = 33364,
		GL_UNKNOWN_CONTEXT_RESET  = 33365;

	/** Accepted by the {@code value} parameter of GetBooleanv, GetIntegerv, and GetFloatv. */
	public static final int GL_RESET_NOTIFICATION_STRATEGY = 33366;

	/** Returned by GetIntegerv and related simple queries when {@code value} is {@link #GL_RESET_NOTIFICATION_STRATEGY RESET_NOTIFICATION_STRATEGY}. */
	public static final int
		GL_LOSE_CONTEXT_ON_RESET = 33362,
		GL_NO_RESET_NOTIFICATION = 33377;

	/** Returned by GetIntegerv when {@code pname} is CONTEXT_FLAGS. */
	public static final int GL_CONTEXT_FLAG_ROBUST_ACCESS_BIT = 4;

	/** Returned by {@link GL11#glGetError GetError}. */
	public static final int GL_CONTEXT_LOST = 1287;

	/** Accepted by the {@code pname} parameters of GetBooleanv, GetIntegerv, GetFloatv, and GetDoublev. */
	public static final int GL_RGBA_INTEGER_MODE_EXT = 36254;

	/** Accepted by the {@code internalFormat} parameter of TexImage1D, TexImage2D, and TexImage3D. */
	public static final int
		GL_RGBA32UI_EXT            = 36208,
		GL_RGB32UI_EXT             = 36209,
		GL_ALPHA32UI_EXT           = 36210,
		GL_INTENSITY32UI_EXT       = 36211,
		GL_LUMINANCE32UI_EXT       = 36212,
		GL_LUMINANCE_ALPHA32UI_EXT = 36213,
		GL_RGBA16UI_EXT            = 36214,
		GL_RGB16UI_EXT             = 36215,
		GL_ALPHA16UI_EXT           = 36216,
		GL_INTENSITY16UI_EXT       = 36217,
		GL_LUMINANCE16UI_EXT       = 36218,
		GL_LUMINANCE_ALPHA16UI_EXT = 36219,
		GL_RGBA8UI_EXT             = 36220,
		GL_RGB8UI_EXT              = 36221,
		GL_ALPHA8UI_EXT            = 36222,
		GL_INTENSITY8UI_EXT        = 36223,
		GL_LUMINANCE8UI_EXT        = 36224,
		GL_LUMINANCE_ALPHA8UI_EXT  = 36225,
		GL_RGBA32I_EXT             = 36226,
		GL_RGB32I_EXT              = 36227,
		GL_ALPHA32I_EXT            = 36228,
		GL_INTENSITY32I_EXT        = 36229,
		GL_LUMINANCE32I_EXT        = 36230,
		GL_LUMINANCE_ALPHA32I_EXT  = 36231,
		GL_RGBA16I_EXT             = 36232,
		GL_RGB16I_EXT              = 36233,
		GL_ALPHA16I_EXT            = 36234,
		GL_INTENSITY16I_EXT        = 36235,
		GL_LUMINANCE16I_EXT        = 36236,
		GL_LUMINANCE_ALPHA16I_EXT  = 36237,
		GL_RGBA8I_EXT              = 36238,
		GL_RGB8I_EXT               = 36239,
		GL_ALPHA8I_EXT             = 36240,
		GL_INTENSITY8I_EXT         = 36241,
		GL_LUMINANCE8I_EXT         = 36242,
		GL_LUMINANCE_ALPHA8I_EXT   = 36243;

	/** Accepted by the {@code format} parameter of TexImage1D, TexImage2D, TexImage3D, TexSubImage1D, TexSubImage2D, TexSubImage3D, DrawPixels and ReadPixels. */
	public static final int
		GL_RED_INTEGER_EXT             = 36244,
		GL_GREEN_INTEGER_EXT           = 36245,
		GL_BLUE_INTEGER_EXT            = 36246,
		GL_ALPHA_INTEGER_EXT           = 36247,
		GL_RGB_INTEGER_EXT             = 36248,
		GL_RGBA_INTEGER_EXT            = 36249,
		GL_BGR_INTEGER_EXT             = 36250,
		GL_BGRA_INTEGER_EXT            = 36251,
		GL_LUMINANCE_INTEGER_EXT       = 36252,
		GL_LUMINANCE_ALPHA_INTEGER_EXT = 36253;

	/** Accepted by the {@code value} parameter of GetTexLevelParameter. */
	public static final int
		GL_TEXTURE_RED_TYPE_ARB       = 35856,
		GL_TEXTURE_GREEN_TYPE_ARB     = 35857,
		GL_TEXTURE_BLUE_TYPE_ARB      = 35858,
		GL_TEXTURE_ALPHA_TYPE_ARB     = 35859,
		GL_TEXTURE_LUMINANCE_TYPE_ARB = 35860,
		GL_TEXTURE_INTENSITY_TYPE_ARB = 35861,
		GL_TEXTURE_DEPTH_TYPE_ARB     = 35862;

	/** Returned by the {@code params} parameter of GetTexLevelParameter. */
	public static final int GL_UNSIGNED_NORMALIZED_ARB = 35863;

	/** Accepted by the {@code internalFormat} parameter of TexImage1D, TexImage2D, and TexImage3D. */
	public static final int
		GL_RGBA32F_ARB            = 34836,
		GL_RGB32F_ARB             = 34837,
		GL_ALPHA32F_ARB           = 34838,
		GL_INTENSITY32F_ARB       = 34839,
		GL_LUMINANCE32F_ARB       = 34840,
		GL_LUMINANCE_ALPHA32F_ARB = 34841,
		GL_RGBA16F_ARB            = 34842,
		GL_RGB16F_ARB             = 34843,
		GL_ALPHA16F_ARB           = 34844,
		GL_INTENSITY16F_ARB       = 34845,
		GL_LUMINANCE16F_ARB       = 34846,
		GL_LUMINANCE_ALPHA16F_ARB = 34847;
	
	/**
	 * Accepted by the {@code internalformat} parameter of TexImage2D, CopyTexImage2D, and CompressedTexImage2D and the {@code format} parameter of
	 * CompressedTexSubImage2D.
	 */
	public static final int
		GL_COMPRESSED_RGB_S3TC_DXT1_EXT  = 33776,
		GL_COMPRESSED_RGBA_S3TC_DXT1_EXT = 33777,
		GL_COMPRESSED_RGBA_S3TC_DXT3_EXT = 33778,
		GL_COMPRESSED_RGBA_S3TC_DXT5_EXT = 33779;

}
