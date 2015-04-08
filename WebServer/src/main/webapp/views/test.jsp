<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Direction-Aware Hover Effect with CSS3 and jQuery</title>

    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">


    <style>
        @import url(http://fonts.googleapis.com/css?family=Roboto:400,300);

        * {
            font-family: 'Roboto', sans-serif;
        }

        body {
        //background: #3498db;
        }

        #tri-d-wrapper {
            display: inline-block;
        //  background-color: #007fff;
            -webkit-perspective: 300px;
            perspective: 300px;
            position: relative;
            height: 280px;
            width: 360px;
        }

        #img-wrapper {
            width: 350px;
            background: #3498db;
            margin: 0;
            position: absolute;
            cursor: pointer;
            border-radius: 3px;
            overflow: hidden;
            top: 50%;
            left: 50%;
            margin-left: -175px;
            margin-top: -131px;
            -webkit-transform: rotateX(30deg) scale(.65);
            transform: rotateX(30deg) scale(.65);
            -webkit-transition: .3s all ease;
            transition: .3s all ease;
            box-shadow: 0 15px 20px 5px rgba(0,0,0,.2);
        }

        #img-wrapper:hover {
            -webkit-transform: rotateX(0deg) scale(1);
            transform: rotateX(0deg) scale(1);
            box-shadow: none;
        }

        #img-wrapper img {
            width: 100%;
            margin: 0;
            position: relative;
            display: block;
            -webkit-transition: .3s all ease;
            transition: .3s all ease;
        }

        #img-wrapper:hover img {
            -webkit-transform: scale(1.5);
            -ms-transform: scale(1.5);
            transform: scale(1.5);
        }

        #img-wrapper:hover figcaption {
            background: rgba(0,0,0,.75);
        }

        #img-wrapper:hover figcaption h2 {
            opacity: 1;
            padding-left: 20px;
            -webkit-transition: .3s all ease .3s;
            transition: .3s all ease .3s;
        }

        #img-wrapper:hover figcaption .bottom-detail {

            background-color: #ffead4;
            -webkit-transform: translateY(0);
            -ms-transform: translateY(0);
            transform: translateY(0);
        }

        #img-wrapper:hover figcaption .bottom-detail p {
            opacity: 1;
            top: 0;
            -webkit-transition: .3s all ease .5s;
            transition: .3s all ease .5s;
        }

        #img-wrapper:hover figcaption .bottom-detail .social-icons li a {
            -webkit-transform: scale(1);
            -ms-transform: scale(1);
            transform: scale(1);
            -webkit-transition: .3s all ease .5s;
            transition: .3s all ease .5s;
        }

        #img-wrapper:hover figcaption .bottom-detail .social-icons li a.fa-twitter {
            color: #50ABF1;
            -webkit-transition: .3s all ease .7s;
            transition: .3s all ease .7s;
        }

        #img-wrapper:hover figcaption .bottom-detail .social-icons li a.fa-facebook {
            color: #3B599A;
        }

        #img-wrapper:hover figcaption .bottom-detail .social-icons li a.fa-dribbble {
            color: EC4989;
            -webkit-transition: .3s all ease .6s;
            transition: .3s all ease .6s;
        }

        #img-wrapper:hover figcaption .bottom-detail .social-icons li a.fa-behance {
            color: #1769ff;
            -webkit-transition: .3s all ease .8s;
            transition: .3s all ease .8s;
        }

        figcaption {
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            -webkit-transition: .3s all ease;
            transition: .3s all ease;
        }

        figcaption h2 {
            margin: 0;
            color: white;
            font-size: 22px;
            line-height: 70px;
            opacity: 0;
            padding-left: 0;
            padding-right: 20px;
            font-weight: 300;
        }

        figcaption .bottom-detail {
            position: absolute;
            width: 100%;
            bottom: 0;
            top: 70px;
            background: white;
            -webkit-transform: translateY(100%);
            -ms-transform: translateY(100%);
            transform: translateY(100%);
            -webkit-transition: .3s all ease .1s;
            transition: .3s all ease .1s;
        }

        .bottom-detail p {
            font-size: 17px;
            opacity: 0;
            top: 20px;
            padding-left: 20px;
            padding-right: 20px;
            color: #999;
            font-weight: 300;
            line-height: 1.5;
        }

        .bottom-detail .social-icons {
            padding: 0 20px;
            box-sizing: border-box;
            margin: 0;
            position: absolute;
            bottom: 0;
            width: 100%;
            list-style: none;
            text-align: center;
        }

        .social-icons li {
            display: inline-block;
            margin: 0;
        }

        .social-icons li a {
            font-size: 22px;
            text-decoration: none;
            padding: 22px 15px;
            -webkit-transform: scale(0);
            -ms-transform: scale(0);
            transform: scale(0);
        }

        .fa-twitter {
            color: #50ABF1;
        }

        .fa-facebook {
            color: #3B599A;
        }

        .fa-dribbble {
            color: #EC4989;
        }

        .fa-behance {
            color: #1769ff;
        }

    </style>


</head>
<body>

<div id="tri-d-wrapper">
    <figure id="img-wrapper">
        <img src="https://d13yacurqjgara.cloudfront.net/users/43342/screenshots/1973147/studiojq2015_explore62_1x.jpg"
             alt="Preview Image">
        <figcaption>
            <h2 class="title">Husty jako prase</h2>

            <div class="bottom-detail">
                <p>Some little detail here, things about the designs summary lorem anything related just to make
                    this look long text.</p>
                <ul class="social-icons">
                    <li><a class="fa fa-facebook" href="#"></a></li>
                    <li><a class="fa fa-dribbble" href="#"></a></li>
                    <li><a class="fa fa-twitter" href="#"></a></li>
                    <li><a class="fa fa-behance" href="#"></a></li>
                </ul>
            </div>
        </figcaption>
    </figure>
</div>

<div id="tri-d-wrapper">
    <figure id="img-wrapper">
        <img src="https://d13yacurqjgara.cloudfront.net/users/43342/screenshots/1973147/studiojq2015_explore62_1x.jpg"
             alt="Preview Image">
        <figcaption>
            <h2 class="title">Husty jako prase</h2>

            <div class="bottom-detail">
                <p>Some little detail here, things about the designs summary lorem anything related just to make
                    this look long text.</p>
                <ul class="social-icons">
                    <li><a class="fa fa-facebook" href="#"></a></li>
                    <li><a class="fa fa-dribbble" href="#"></a></li>
                    <li><a class="fa fa-twitter" href="#"></a></li>
                    <li><a class="fa fa-behance" href="#"></a></li>
                </ul>
            </div>
        </figcaption>
    </figure>
</div>

<div id="tri-d-wrapper">
    <figure id="img-wrapper">
        <img src="https://d13yacurqjgara.cloudfront.net/users/43342/screenshots/1973147/studiojq2015_explore62_1x.jpg"
             alt="Preview Image">
        <figcaption>
            <h2 class="title">Husty jako prase</h2>

            <div class="bottom-detail">
                <p>Some little detail here, things about the designs summary lorem anything related just to make
                    this look long text.</p>
                <ul class="social-icons">
                    <li><a class="fa fa-facebook" href="#"></a></li>
                    <li><a class="fa fa-dribbble" href="#"></a></li>
                    <li><a class="fa fa-twitter" href="#"></a></li>
                    <li><a class="fa fa-behance" href="#"></a></li>
                </ul>
            </div>
        </figcaption>
    </figure>
</div>


</body>
</html>