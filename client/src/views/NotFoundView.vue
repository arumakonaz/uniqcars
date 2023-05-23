<template>
    <div class="d-flex align-items-center justify-content-center vh-100 text-light">
        <div class="text-center">
            <h1 class="display-1 fw-bold">404</h1>
            <p class="fs-3">
                <span class="text-danger">
                    {{$t('oops')}}
                </span>
                {{$t('not_found')}}
            </p>
            <p class="lead">
                {{$t('not_found_lead')}}
            </p>
            <b-link to="/" class="btn btn-info">
                {{ $t('go_home') }}
            </b-link>
        </div>
    </div>
</template>