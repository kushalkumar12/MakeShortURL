<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ShortURL - Free URL Shortener</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            padding: 0;
            background: #f4f6f8;
            color: #333;
        }

        .container {
            max-width: 700px;
            margin: 0 auto;
            padding: 40px 20px;
        }

        header {
            text-align: center;
            margin-bottom: 40px;
        }

        .logo {
            font-size: 2.8rem;
            font-weight: bold;
            color: #2c3e50;
        }

        .logo .highlight {
            background-color: #3498db;
            color: #fff;
            padding: 0 6px;
            border-radius: 4px;
        }

        .tagline {
            font-size: 1.1rem;
            color: #7f8c8d;
            margin-top: 10px;
        }

        .url-form {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.06);
        }

        .url-form input[type="url"] {
            width: 95%;
            padding: 14px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 1rem;
            margin-bottom: 20px;
        }

        .submit-link {
            display: inline-block;
            padding: 12px 24px;
            background-color: #3498db;
            color: #fff;
            border-radius: 6px;
            text-decoration: none;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        .submit-link:hover {
            background-color: #2c80b4;
        }

        .result {
            display: none;
            background: #ecf9ff;
            padding: 15px;
            margin-top: 20px;
            border-left: 4px solid #3498db;
            border-radius: 4px;
        }

        .features {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            margin-top: 50px;
        }

        .feature {
            flex: 1 1 30%;
            background: white;
            border-radius: 6px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
            padding: 20px;
            text-align: center;
            margin: 10px;
        }

        footer {
            text-align: center;
            font-size: 0.9em;
            color: #aaa;
            margin-top: 60px;
        }

        .static-links {
            margin-top: 40px;
        }

        .static-links h4 {
            margin-bottom: 10px;
            font-weight: normal;
        }

        .static-link-item {
            display: flex;
            justify-content: space-between;
            background: #fff;
            padding: 12px 16px;
            margin-bottom: 10px;
            border-radius: 6px;
            box-shadow: 0 1px 5px rgba(0,0,0,0.05);
        }

        .static-link-item a {
            color: #3498db;
            text-decoration: none;
        }
		.stats {
            display: flex;
            justify-content: center;
            gap: 30px;
            margin: 40px 0;
            flex-wrap: wrap;
        }

		.stat-item {
		    text-align: center;
		    min-width: 150px;
		}

		.stat-number {
		    font-size: 2.5rem;
		    font-weight: 700;
		    color: var(--primary-color);
		    margin-bottom: 5px;
		}

		.stat-label {
		    font-size: 0.9rem;
		    opacity: 0.8;
		}

    </style>
</head>
<body>
    <div class="container">
        <header>
            <div class="logo">Mini <span class="highlight">URL</span></div>
            <div class="tagline">Professional, Fast, and Reliable URL Shortener</div>
        </header>
		
		<div class="stats">
            <div class="stat-item">
                <div class="stat-number" id="linksShortened">10+</div>
                <div class="stat-label">Links Shortened</div>
            </div>
            <div class="stat-item">
                <div class="stat-number" id="clicksRedirected">10+</div>
                <div class="stat-label">Clicks Redirected</div>
            </div>
        </div>

        <div class="url-form">
            <form name = "mini" method="post">
                <input type="url" id="originalUrl" name="lengthUrl" placeholder="Enter a long URL (e.g. https://...)" required>
                <a href="#" class="submit-link" onclick="submitForm(); loadURLCount();">Shorten Now</a>
            </form>
            <div class="result" id="result">
                <p>Shortened URL: <a href="#" id="shortUrl" target="_blank"></a></p>
            </div>
        </div>

        <div class="static-links">
            <h4>Recently Shortened URLs</h4>
            <div class="static-link-item">
                <a href="https://tinyurl.com/abc123" target="_blank">https://tinyurl.com/abc123</a>
                <span>Clicks: 120</span>
            </div>
            <div class="static-link-item">
                <a href="https://tinyurl.com/xyz789" target="_blank">https://tinyurl.com/xyz789</a>
                <span>Clicks: 98</span>
            </div>
            <div class="static-link-item">
                <a href="https://tinyurl.com/lmn456" target="_blank">https://tinyurl.com/lmn456</a>
                <span>Clicks: 43</span>
            </div>
        </div>

        <footer>
            &copy; 2025 ShortURL Inc. All rights reserved.
        </footer>
    </div>
    <script>
        function submitForm() {
            const form = document.getElementById('shortenForm');
            const originalUrl = document.getElementById('originalUrl').value;
            if (!originalUrl) return;

            const resultDiv = document.getElementById('result');
            const shortUrlEl = document.getElementById('shortUrl');
			
			$.ajax({
					url  : "/long/to/short/in",
					type : "POST",
					async: false,
					data : {lengthUrl:originalUrl},
					dataType: 'json',
					success : function(jsonData) {
						shortUrlEl.textContent = "";
					}
			});

        }
		
		function loadURLCount(){
			
			let linksGenerated = 10;
			let clickRedirected = 15;
			
			const stats = [
			                { element: 'linksShortened', target: linksGenerated, duration: 200 },
			                { element: 'clicksRedirected', target: clickRedirected, duration: 200 }
			            ];
			stats.forEach(stat => {
			               const element = document.getElementById(stat.element);
			               const start = 0;
			               const increment = stat.target / (stat.duration / 16); // 60fps
			               let current = start;
			               const timer = setInterval(() => {
			                   current += increment;
			                   if (current >= stat.target) {
			                       clearInterval(timer);
			                       element.textContent = stat.target.toLocaleString() + '+';
			                   } else {
			                       element.textContent = Math.floor(current).toLocaleString() + '+';
			                   }
			               }, 16);
			           });
		}
    </script>
</body>
</html>