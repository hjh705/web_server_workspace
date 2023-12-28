tailwind.config = {
    theme: {
        extend: {
            colors: {
                primary: {
                    "50": "#eff6ff",
                    "100": "#dbeafe",
                    "200": "#bfdbfe",
                    "300": "#93c5fd",
                    "400": "#60a5fa",
                    "500": "#3b82f6",
                    "600": "#2563eb",
                    "700": "#1d4ed8",
                    "800": "#1e40af",
                    "900": "#1e3a8a",
                    "950": "#172554"
                }
            },
            keyframes: {
                bell: { // 종이 흔들리는 애니메이션
                    "0%, 50%": {
                        transform: "rotate(0deg)",
                    },
                    "5%, 15%, 25%, 35%, 45%": {
                        transform: "rotate(13deg)",
                    },
                    "10%, 20%, 30%, 40%": {
                        transform: "rotate(-13deg)",
                    },
                },
            },
            animation: { // animate-bell 로 사용
                bell: "bell 2s infinite linear",
            }
        },
        container: {
            center: true
        },
        fontFamily: {
            'sans': ['Gowun Dodum', 'sans-serif']
        }
    }
}