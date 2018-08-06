package net.skyscaner.backpack

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.TextView


class BpkBadge(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int) : TextView(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.style.Bpk_badge)

    init {
        initialize(context, attrs, defStyleAttr)
        setup()
    }

    var mType: BpkBadgeType = BpkBadgeType.BPKBadgeTypeSuccess
        set(value) {
            field = value
            setup()
        }
    var message: String? = null

    private fun initialize(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {

        val a: TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.badge,
                0, 0)

        if (a.hasValue(R.styleable.badge_bpk_badge_type)) {
            mType = BpkBadgeType.fromId(a.getInt(R.styleable.badge_bpk_badge_type, 0))
        }
        if (a.hasValue(R.styleable.badge_bpk_message)) {
            message = a.getString(R.styleable.badge_bpk_message)
        }

        a.recycle()
    }

    fun setup() {
        if (message != null) {
            this.text = message
        }

        //set padding
        val paddingMd = resources.getDimension(R.dimen.bpkSpacingMd).toInt()
        val paddingSm = resources.getDimension(R.dimen.bpkSpacingSm).toInt()
        this.setPadding(paddingMd, paddingSm, paddingMd, paddingSm)

        //set Text color
        this.setTextColor(context.resources.getColor(mType.textColor))

        // Set background color
        val border = GradientDrawable()
        border.setColor(context.resources.getColor(mType.bgColor))

        //Set border
        if (mType == BpkBadgeType.BPKBadgeTypeOutline) {
            border.setStroke(resources.getDimension(R.dimen.badge_border_size).toInt(), context.resources.getColor(R.color.bpkWhite))
            border.setColor(context.resources.getColor(mType.bgColor) and 0x32ffffff)
        }

        //set corner radius
        val cornerRadius = context.resources.getDimension(R.dimen.bpkBorderRadiusSm)

        val radius = floatArrayOf(cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius,
                cornerRadius, cornerRadius)
        border.cornerRadii = radius
        this.background = border

    }

    enum class BpkBadgeType constructor(internal var id: Int, var bgColor: Int, var textColor: Int) {
        BPKBadgeTypeSuccess(1, R.color.bpkGreen500, R.color.bpkGray700),
        BPKBadgeTypeWarning(2, R.color.bpkYellow500, R.color.bpkGray700),
        BPKBadgeTypeDestructive(3, R.color.bpkRed500, R.color.bpkWhite),
        BPKBadgeTypeLight(4, R.color.bpkGray50, R.color.bpkGray700),
        BPKBadgeTypeInverse(5, R.color.bpkWhite, R.color.bpkGray700),
        BPKBadgeTypeOutline(6, R.color.bpkWhite, R.color.bpkWhite);

        companion object {

            internal fun fromId(id: Int): BpkBadgeType {
                for (f in values()) {
                    if (f.id == id) return f
                }
                throw IllegalArgumentException()
            }
        }
    }
}
